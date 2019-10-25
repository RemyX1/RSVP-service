package com.company.rsvpedgeservice.util;


import com.company.rsvpedgeservice.dto.Rsvp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "rsvp-backing")
public interface RsvpClient {

    @PostMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp saveRsvp(@RequestBody @Valid Rsvp rsvp);

    @GetMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.OK)
    public List<Rsvp> findAllRsvps();

    @GetMapping(value = "/rsvps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Rsvp findRsvpById(@PathVariable int id);

    @PutMapping(value = "/rsvps")
    @ResponseStatus(HttpStatus.OK)
    public void updateRsvp(@RequestBody @Valid Rsvp rsvp);

    @DeleteMapping(value = "/rsvps/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRsvp(@PathVariable int id);

}
