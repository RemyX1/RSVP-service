package com.company.rsvpbacking.repository;

import com.company.rsvpbacking.dto.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsvpRepository extends JpaRepository<Rsvp,Integer> {
}
