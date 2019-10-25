package com.company.rsvpbacking.repository;

import com.company.rsvpbacking.dto.Rsvp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RsvpRepositoryTest {

    @Autowired
    RsvpRepository repo;

    @BeforeEach
    void setUp() {
        repo.deleteAll();
    }

    @Test
    public void saveRsvpShouldReturnRsvp(){
        Rsvp rsvp = new Rsvp("Remmy", 2, "5555555555");
        rsvp = repo.save(rsvp);

        assertEquals(rsvp, repo.findById(rsvp.getId()).get());
    }

    @Test
    public void findRsvpByIdShouldReturnRsvp(){
        Rsvp rsvp = new Rsvp("Remmy", 2, "5555555555");
        rsvp = repo.save(rsvp);

        assertEquals(rsvp, repo.findById(rsvp.getId()).get());
    }

    @Test
    public void findAllShouldReturnListOfRsvps(){
        Rsvp rsvp = new Rsvp("Remmy", 2, "5555555555");
        rsvp = repo.save(rsvp);

        Rsvp rsvp1 = new Rsvp("Nadine", 3, "5555555554");
        rsvp1 = repo.save(rsvp1);

        assertEquals(2, repo.findAll().size());
    }

    @Test
    public void saveRsvpShouldUpdateExistingRsvp(){
        Rsvp rsvp = new Rsvp("Remmy", 2, "5555555555");
        rsvp = repo.save(rsvp);

        rsvp.setGuestName("Raymond");
        repo.save(rsvp);

        assertEquals(rsvp, repo.findById(rsvp.getId()).get());
        assertEquals("Raymond", repo.findById(rsvp.getId()).get().getGuestName());
    }

    @Test
    public void deleteRsvpByIdShouldDeleteRsvp(){
        Rsvp rsvp = new Rsvp("Remmy", 2, "5555555555");
        rsvp = repo.save(rsvp);
        assertEquals(rsvp, repo.findById(rsvp.getId()).get());

        repo.deleteById(rsvp.getId());
        assertTrue(!repo.findById(rsvp.getId()).isPresent());
    }
}