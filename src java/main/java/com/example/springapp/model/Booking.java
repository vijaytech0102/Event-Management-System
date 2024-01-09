package com.example.springapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long bookingId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date submissionDate;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date eventDate;
	private String bookingStatus;
	private Integer headcount;          
	private Double amount;
   
    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id")
	private Event event;
    // @JsonIgnore
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Payment payment;

    @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
	private Organizer organizer;

	public Booking(Date submissionDate, String description, Date eventDate, String bookingStatus, Integer headcount,
			Double amount, Event event, Payment payment, Organizer organizer) {
		super();
		this.submissionDate = submissionDate;
		this.description = description;
		this.eventDate = eventDate;
		this.bookingStatus = bookingStatus;
		this.headcount = headcount;
		this.amount = amount;
		this.event = event;
		this.payment = payment;
		this.organizer = organizer;
	}

	public Booking(Long bookingId, Date submissionDate, String description, Date eventDate, String bookingStatus,
			Integer headcount, Double amount, Event event, Payment payment, Organizer organizer) {
		super();
		this.bookingId = bookingId;
		this.submissionDate = submissionDate;
		this.description = description;
		this.eventDate = eventDate;
		this.bookingStatus = bookingStatus;
		this.headcount = headcount;
		this.amount = amount;
		this.event = event;
		this.payment = payment;
		this.organizer = organizer;
	}

	public Booking() {
		super();
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getHeadcount() {
		return headcount;
	}

	public void setHeadcount(Integer headcount) {
		this.headcount = headcount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	} 
}