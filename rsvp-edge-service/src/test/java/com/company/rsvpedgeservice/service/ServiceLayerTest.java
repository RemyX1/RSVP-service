package com.company.rsvpedgeservice.service;

import com.company.rsvpedgeservice.dto.Rsvp;
import com.company.rsvpedgeservice.util.RsvpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class ServiceLayerTest {

    RsvpClient rsvpClient;
    ServiceLayer serviceLayer;

    private Rsvp rsvp;




    @BeforeEach
    void setUp() {

        setUpMock();

        rsvp = new Rsvp();
        rsvp.setGuestName("Deep");
        rsvp.setPhoneNumber("770-165-1797");
        rsvp.setTotalAttending(4);

        this.serviceLayer = new ServiceLayer(rsvpClient);

    }

    @Test
    void createRsvp() {

        Rsvp test = serviceLayer.createRsvp(rsvp);

        assertEquals(test.getRsvpId(),1);
    }

    @Test
    void createInvalidRsvpShouldReturnNull() {

        rsvp.setTotalAttending(6);
        assertNull(rsvpClient.saveRsvp(rsvp));

    }



    public void setUpMock() {

        rsvpClient = mock(RsvpClient.class);

        Rsvp rsvp = new Rsvp();
        rsvp.setGuestName("Deep");
        rsvp.setPhoneNumber("770-165-1797");
        rsvp.setTotalAttending(4);

        Rsvp rsvp1 = new Rsvp();
        rsvp1.setRsvpId(1);
        rsvp1.setGuestName("Deep");
        rsvp1.setPhoneNumber("770-165-1797");
        rsvp1.setTotalAttending(4);

        Rsvp rsvp2 = new Rsvp();
        rsvp2.setGuestName("Deep");
        rsvp2.setPhoneNumber("770-165-1797");
        rsvp2.setTotalAttending(6);

        doReturn(rsvp1).when(rsvpClient).saveRsvp(rsvp);
        doReturn(null).when(rsvpClient).saveRsvp(rsvp2);


    }


}