package com.example.springapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="event")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eventId;
	private String eventType;
	private String description;
	private Integer participantsCount;
	private Integer eventCharges;
	
	
    
    
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    
	private List<Facility> facilities=new ArrayList<>();		
    @JsonIgnore
	@OneToMany(targetEntity=Booking.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	
    private List<Booking> bookings=new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST,CascadeType.MERGE,} ,
			targetEntity = Organizer.class)
   
	private List<Organizer> organizers =new ArrayList<>();
	public Event() {
		super();
	}
	public Event(String eventType, String description, List<Facility> facilities, Integer participantsCount,
			Integer eventCharges, List<Booking> bookings, List<Organizer> organizers) {
		super();
		this.eventType = eventType;
		this.description = description;
		this.facilities = facilities;
		this.participantsCount = participantsCount;
		this.eventCharges = eventCharges;
		this.bookings = bookings;
		this.organizers = organizers;
	}
	public Event(Long eventId, String eventType, String description, List<Facility> facilities,
			Integer participantsCount, Integer eventCharges, List<Booking> bookings, List<Organizer> organizers) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.description = description;
		this.facilities = facilities;
		this.participantsCount = participantsCount;
		this.eventCharges = eventCharges;
		this.bookings = bookings;
		this.organizers = organizers;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Facility> getFacilities() {
		return facilities;
	}
	public void setFacilities(List<Facility> facilities) {
		this.facilities = facilities;
	}
	public Integer getParticipantsCount() {
		return participantsCount;
	}
	public void setParticipantsCount(Integer participantsCount) {
		this.participantsCount = participantsCount;
	}
	public Integer getEventCharges() {
		return eventCharges;
	}
	public void setEventCharges(Integer eventCharges) {
		this.eventCharges = eventCharges;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public List<Organizer> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<Organizer> organizers) {
		this.organizers = organizers;
	}
    @Override
    public String toString() {
        return "Event [bookings=" + bookings + ", description=" + description + ", eventCharges=" + eventCharges
                + ", eventId=" + eventId + ", eventType=" + eventType + ", facilities=" + facilities + ", organizers="
                + organizers + ", participantsCount=" + participantsCount + "]";
    }

    
	
}

    

