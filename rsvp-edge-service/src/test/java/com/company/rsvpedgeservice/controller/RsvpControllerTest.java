package com.company.rsvpedgeservice.controller;

import com.company.rsvpedgeservice.dto.Rsvp;
import com.company.rsvpedgeservice.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
class RsvpControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer service;

    @MockBean
    private DataSource data;

    private ObjectMapper mapper = new ObjectMapper();

    private Rsvp rsvp, outputRsvp;

    @BeforeEach
    void setUp() {


        rsvp = new Rsvp();
        rsvp.setGuestName("Deep");
        rsvp.setPhoneNumber("770-165-1797");
        rsvp.setTotalAttending(4);

        outputRsvp = new Rsvp();
        outputRsvp.setGuestName("Deep");
        outputRsvp.setPhoneNumber("770-165-1797");
        outputRsvp.setTotalAttending(4);



    }

    @Test
    void createRsvp() throws Exception {

        String inputJson = mapper.writeValueAsString(rsvp);


        //Object to JSON in String
        String outputJson = mapper.writeValueAsString(outputRsvp);

        when(service.createRsvp(rsvp)).thenReturn(outputRsvp);



        this.mockMvc.perform(post("/rsvp")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().json(outputJson));




    }
}