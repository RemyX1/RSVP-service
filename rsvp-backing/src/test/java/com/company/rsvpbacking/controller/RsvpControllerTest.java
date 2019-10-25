package com.company.rsvpbacking.controller;

import com.company.rsvpbacking.dto.Rsvp;
import com.company.rsvpbacking.service.RsvpServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RsvpController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
class RsvpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RsvpServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void saveRsvpShouldRetunRsvpAndStatusIsCreated() throws Exception{
        Rsvp inputRsvp = new Rsvp("Remmy", 2, "5555555555");
        String inputJson = mapper.writeValueAsString(inputRsvp);

        Rsvp outputRsvp = new Rsvp(1, "Remmy", 2, "5555555555");
        String outputJson = mapper.writeValueAsString(outputRsvp);

        when(serviceLayer.saveRsvp(inputRsvp)).thenReturn(outputRsvp);

        this.mockMvc.perform(post("/rsvps")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    void findAllRsvpsShouldReturnListOfRsvpsAndStatusIsOk() throws Exception{
        Rsvp outputRsvp = new Rsvp(1, "Remmy", 2, "5555555555");

        List<Rsvp> rsvpList = new ArrayList<>();
        rsvpList.add(outputRsvp);

        when(serviceLayer.findAllRsvps()).thenReturn(rsvpList);

        List<Rsvp> listChecker = new ArrayList<>();
        listChecker.addAll(rsvpList);

        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/rsvps"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void findRsvpByIdShouldReturnRsvpAndStatusIsOk() throws Exception{
        Rsvp outputRsvp = new Rsvp(1, "Remmy", 2, "5555555555");
        String outputJson = mapper.writeValueAsString(outputRsvp);

        when(serviceLayer.findRsvpById(1)).thenReturn(outputRsvp);

        this.mockMvc.perform(get("/rsvps/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    void getRsvpThatDoesNotExistShouldReturn404() throws Exception{
        int id = 90000;

        when(serviceLayer.findRsvpById(id)).thenReturn(null);

        this.mockMvc.perform(get("/rsvps/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateRsvpShouldReturnStatusOk() throws Exception{
        Rsvp inputRsvp = new Rsvp(1, "Remmy", 2, "5555555555");
        String inputJson = mapper.writeValueAsString(inputRsvp);

        this.mockMvc.perform(put("/rsvps")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteRsvpShouldReturnStatusIsOk() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/rsvps/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}