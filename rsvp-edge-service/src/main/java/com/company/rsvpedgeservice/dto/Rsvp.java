package com.company.rsvpedgeservice.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class Rsvp {


    private int rsvpId;
    private String guestName;
    @Min(1)
    @Max(4)
    private int totalAttending;
    private String phoneNumber;

    public int getRsvpId() {
        return rsvpId;
    }

    public Rsvp setRsvpId(int rsvpId) {
        this.rsvpId = rsvpId;
        return this;
    }

    public String getGuestName() {
        return guestName;
    }

    public Rsvp setGuestName(String guestName) {
        this.guestName = guestName;
        return this;
    }

    public int getTotalAttending() {
        return totalAttending;
    }

    public Rsvp setTotalAttending(int totalAttending) {
        this.totalAttending = totalAttending;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Rsvp setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rsvp rsvp = (Rsvp) o;
        return rsvpId == rsvp.rsvpId &&
                totalAttending == rsvp.totalAttending &&
                Objects.equals(guestName, rsvp.guestName) &&
                Objects.equals(phoneNumber, rsvp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rsvpId, guestName, totalAttending, phoneNumber);
    }
}
