package com.company.rsvpbacking.service;

import com.company.rsvpbacking.dto.Rsvp;
import com.company.rsvpbacking.repository.RsvpRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RsvpServiceLayerTest {

    private RsvpRepository repo;
    private RsvpServiceLayer serviceLayer;

    //mock to mimic the behavior of rsvprepository
    private void setUpRepoMock(){
        repo = mock(RsvpRepository.class);
        Rsvp rsvp = new Rsvp(1, "Remmy", 2, "5555555555");
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        List<Rsvp> rsvpList = new ArrayList<>();
        rsvpList.add(rsvp);

        //will return create values instead to accessing the repository and database
        doReturn(rsvp).when(repo).save(rsvp1);
        doReturn(Optional.of(rsvp)).when(repo).findById(1);
        doReturn(rsvpList).when(repo).findAll();
    }

    @BeforeEach
    void setUp() {
        setUpRepoMock();
        serviceLayer = new RsvpServiceLayer(repo);
    }

    @Test
    void saveRsvp() {
        //arrange
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        //act
        rsvp1 = serviceLayer.saveRsvp(rsvp1);

        //assert
        assertEquals(rsvp1, serviceLayer.findRsvpById(rsvp1.getId()));
    }

    @Test
    void findRsvpById() {
        //arrange
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        rsvp1 = serviceLayer.saveRsvp(rsvp1);

        //act and assert
        assertEquals(rsvp1, serviceLayer.findRsvpById(rsvp1.getId()));
    }

    @Test
    void findAllRsvps() {
        //arrange
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        rsvp1 = serviceLayer.saveRsvp(rsvp1);

        //act and assert that there is one guest found
        assertEquals(1, serviceLayer.findAllRsvps().size());
    }

    @Test
    void updateRsvp() {
        //arrange
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        rsvp1 = serviceLayer.saveRsvp(rsvp1);

        ArgumentCaptor<Rsvp> rsvpCaptor = ArgumentCaptor.forClass(Rsvp.class);
        //act
        serviceLayer.updateRsvp(rsvp1);
        //assert that service layer passed the rsvp object as expected
        verify(repo, times(2)).save(rsvpCaptor.capture());
    }

    @Test
    void deleteRsvp() {
        //arrange
        Rsvp rsvp1 = new Rsvp("Remmy", 2, "5555555555");
        rsvp1 = serviceLayer.saveRsvp(rsvp1);

        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        //act
        serviceLayer.deleteRsvp(rsvp1.getId());
        //assert that service layer passed the id value as expected
        verify(repo, times(1)).deleteById(intCaptor.capture());
    }
}