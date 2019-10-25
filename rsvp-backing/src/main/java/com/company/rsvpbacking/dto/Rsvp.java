package com.company.rsvpbacking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "rsvp")
public class Rsvp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(max = 50)
    @NotEmpty
    private String guestName;
    @NotNull
    private Integer totalAttending;
    @Size(max = 20)
    @NotEmpty
    private String phoneNumber;

    public Rsvp(int id, @Size(max = 50) @NotEmpty String guestName, @NotNull Integer totalAttending, @Size(max = 20) @NotEmpty String phoneNumber) {
        this.id = id;
        this.guestName = guestName;
        this.totalAttending = totalAttending;
        this.phoneNumber = phoneNumber;
    }

    public Rsvp(@Size(max = 50) @NotEmpty String guestName, @NotNull Integer totalAttending, @Size(max = 20) @NotEmpty String phoneNumber) {
        this.guestName = guestName;
        this.totalAttending = totalAttending;
        this.phoneNumber = phoneNumber;
    }

    public Rsvp() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Integer getTotalAttending() {
        return totalAttending;
    }

    public void setTotalAttending(Integer totalAttending) {
        this.totalAttending = totalAttending;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rsvp rsvp = (Rsvp) o;
        return id == rsvp.id &&
                guestName.equals(rsvp.guestName) &&
                totalAttending.equals(rsvp.totalAttending) &&
                phoneNumber.equals(rsvp.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guestName, totalAttending, phoneNumber);
    }

    @Override
    public String toString() {
        return "Rsvp{" +
                "id=" + id +
                ", guestName='" + guestName + '\'' +
                ", totalAttending=" + totalAttending +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
