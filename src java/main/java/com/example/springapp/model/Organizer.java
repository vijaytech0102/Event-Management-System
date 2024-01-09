package com.example.springapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Organizer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long organizerId;
    private String userName;
	private Long mobileNumber;
	// @MapsId
	// @OneToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name="user_id")
    // @JsonIgnore
	// private User user;
    // @JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
   
	private List<Booking> bookings =new ArrayList<>();

    @JsonIgnore
	@ManyToMany(
    cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        
        
    })
	@JoinTable(name = "organizer_events",
      joinColumns = { @JoinColumn(name = "organizer_id") },
      inverseJoinColumns = { @JoinColumn(name = "event_id") })
    
	private List<Event> events =new ArrayList<>();


    public Organizer() {
    }


    public Organizer(String userName, Long mobileNumber, List<Booking> bookings, List<Event> events) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.bookings = bookings;
        this.events = events;
    }


    public Organizer(Long organizerId, String userName, Long mobileNumber, List<Booking> bookings,
            List<Event> events) {
        this.organizerId = organizerId;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.bookings = bookings;
        this.events = events;
    }


    public Long getOrganizerId() {
        return organizerId;
    }


    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Long getMobileNumber() {
        return mobileNumber;
    }


    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public List<Booking> getBookings() {
        return bookings;
    }


    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    public List<Event> getEvents() {
        return events;
    }


    public void setEvents(List<Event> events) {
        this.events = events;
    }


    @Override
    public String toString() {
        return "Organizer [bookings=" + bookings + ", events=" + events + ", mobileNumber=" + mobileNumber
                + ", organizerId=" + organizerId + ", userName=" + userName + "]";
    }

	
	
}