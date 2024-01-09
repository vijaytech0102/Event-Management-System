package com.example.springapp.service;

import java.util.Date;
import java.util.List;

import com.example.springapp.model.Booking;
import com.fasterxml.jackson.annotation.JsonFormat;
public class BookingServiceRequest {
    private String userName;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date submissionDate;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date eventDate;
	private String bookingStatus;
	private Integer headCount;          
	private Double amount;

    
    // private Long organizerId;
    private List<Long> facilitiesId;
    private Long eventId;
    public BookingServiceRequest(String userName, Date submissionDate, String description, Date eventDate,
            String bookingStatus, Integer headCount, Double amount, List<Long> facilitiesId, Long eventId) {
        this.userName = userName;
        this.submissionDate = submissionDate;
        this.description = description;
        this.eventDate = eventDate;
        this.bookingStatus = bookingStatus;
        this.headCount = headCount;
        this.amount = amount;
        this.facilitiesId = facilitiesId;
        this.eventId = eventId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
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
    public Integer getHeadCount() {
        return headCount;
    }
    public void setHeadCount(Integer headCount) {
        this.headCount = headCount;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public List<Long> getFacilitiesId() {
        return facilitiesId;
    }
    public void setFacilitiesId(List<Long> facilitiesId) {
        this.facilitiesId = facilitiesId;
    }
    public Long getEventId() {
        return eventId;
    }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
    @Override
    public String toString() {
        return "BookingServiceRequest [amount=" + amount + ", bookingStatus=" + bookingStatus + ", description="
                + description + ", eventDate=" + eventDate + ", eventId=" + eventId + ", facilitiesId=" + facilitiesId
                + ", headCount=" + headCount + ", submissionDate=" + submissionDate + ", userName=" + userName + "]";
    }
    
    
      

}