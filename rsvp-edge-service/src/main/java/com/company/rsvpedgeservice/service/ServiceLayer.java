package com.company.rsvpedgeservice.service;


import com.company.rsvpedgeservice.dto.Rsvp;
import com.company.rsvpedgeservice.util.RsvpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class ServiceLayer {

    @Autowired
    private RsvpClient client;

    public ServiceLayer(RsvpClient client) {
        this.client = client;
    }

    public Rsvp createRsvp(Rsvp rsvp) {

    return client.saveRsvp(rsvp);

    }
}
