package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Booking;
import com.example.springapp.model.Organizer;

import org.springframework.stereotype.Service;
@Service
public interface BookingService {
    Booking createBooking(BookingServiceRequest bookingRequest) throws Exception;
    Booking findByBookingId(Long bookingId);
	List<Booking> getBookings();
	boolean deleteBooking(Long bookingId) ;
	boolean updateBooking(Long bookingId,Booking booking);
    boolean updateBookingStatus(Long bookingId,String bookingStatus);
    // Organizer findOrganizersByUserUserName(String userName);
}
