package com.company.rsvpbacking.controller;

import com.company.rsvpbacking.dto.Rsvp;
import com.company.rsvpbacking.service.RsvpServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class RsvpController {

    @Autowired
    RsvpServiceLayer serviceLayer;

    @PostMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp saveRsvp(@RequestBody @Valid Rsvp rsvp){
        return serviceLayer.saveRsvp(rsvp);
    }

    @GetMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.OK)
    public List<Rsvp> findAllRsvps(){
        return serviceLayer.findAllRsvps();
    }

    @GetMapping(value = "/rsvps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rsvp findRsvpById(@PathVariable int id){
        try{
            int tester = serviceLayer.findRsvpById(id).getId();
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Rsvp found with id: " + id, e
            );
        }
        return serviceLayer.findRsvpById(id);
    }

    @PutMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.OK)
    public void updateRsvp(@RequestBody @Valid Rsvp rsvp){
        serviceLayer.updateRsvp(rsvp);
    }

    @DeleteMapping(value = "/rsvps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRsvp(@PathVariable int id){
        serviceLayer.deleteRsvp(id);
    }
}
