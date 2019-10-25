package com.company.rsvpedgeservice.controller;


import com.company.rsvpedgeservice.dto.Rsvp;
import com.company.rsvpedgeservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RsvpController {


    @Autowired
    ServiceLayer service;

    /** *
     * Rsvp controller used to pass in an Rsvp request body to save to database
     *
     */


    @RequestMapping(value = "/rsvp", method = RequestMethod.POST)
    public Rsvp createRsvp(@RequestBody Rsvp rsvp) {

        return service.createRsvp(rsvp);
    }




}
