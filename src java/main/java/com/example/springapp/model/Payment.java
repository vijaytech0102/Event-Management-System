package com.example.springapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Payment {
	@Id
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long paymentId;
	private Double amount;
    @JsonFormat(pattern="yyyy-MM-dd")
	private   	Date paymentDate;
	private String modeOfPayement;
	@OneToOne
	@MapsId
    @JsonIgnore
	private Booking booking;
	
	public Payment() {
		super();
	}
	public Payment(Double amount, Date paymentDate, String modeOfPayement, Booking booking) {
		super();
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.modeOfPayement = modeOfPayement;
		this.booking = booking;
	}
	public Payment(Long paymentId, Double amount, Date paymentDate, String modeOfPayement, Booking booking) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.modeOfPayement = modeOfPayement;
		this.booking = booking;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getModeOfPayement() {
		return modeOfPayement;
	}
	public void setModeOfPayement(String modeOfPayement) {
		this.modeOfPayement = modeOfPayement;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	

}