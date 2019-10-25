package com.company.rsvpbacking.service;

import com.company.rsvpbacking.dto.Rsvp;
import com.company.rsvpbacking.repository.RsvpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RsvpServiceLayer {
    @Autowired
    RsvpRepository repo;

    public RsvpServiceLayer(RsvpRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public Rsvp saveRsvp(Rsvp rsvp){
        return repo.save(rsvp);
    }

    public Rsvp findRsvpById(int id){
        //get rsvp object from optional
        Rsvp rsvp = repo.findById(id).get();
        //if optional does not contain an rsvp then throw error
        if(!repo.findById(id).isPresent()){
            throw new IllegalArgumentException("No rsvp found with this id");
        }
        //if is present then return rsvp object
        return rsvp;
    }

    public List<Rsvp> findAllRsvps(){
        return repo.findAll();
    }

    @Transactional
    public void updateRsvp(Rsvp rsvp){
        repo.save(rsvp);
    }

    @Transactional
    public void deleteRsvp(int id){
        repo.deleteById(id);
    }
}
