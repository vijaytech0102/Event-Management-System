package com.example.springapp.controller;

import com.example.springapp.model.Booking;
import com.example.springapp.service.BookingService;
import com.example.springapp.service.BookingServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/user/booking")
	public ResponseEntity<?> createBooking(@RequestBody BookingServiceRequest bookingServiceRequest )throws Exception{
        
   
		return new ResponseEntity<>(bookingService.createBooking(bookingServiceRequest),HttpStatus.OK);
	}
    @GetMapping("/user/getBookings")
	public ResponseEntity<?> getBookings(){
		return new ResponseEntity<>(bookingService.getBookings(),HttpStatus.OK);
	}
   
   
   
    @GetMapping("/user/booking/{bookingId}")
	public ResponseEntity<?>findByBookingId(@PathVariable Long bookingId){
		return new ResponseEntity<>(bookingService.getBookings(),HttpStatus.OK);
	}
    @DeleteMapping("/user/deleteBooking/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId ){
		return new ResponseEntity<>(bookingService.deleteBooking(bookingId),HttpStatus.OK);
	}

    @PutMapping("/user/booking/{bookingId}")
	public ResponseEntity<?> updateOrganizer(@PathVariable Long bookingId,@RequestBody Booking booking){
		return new ResponseEntity<>(bookingService.updateBooking(bookingId, booking),HttpStatus.OK);
	}
   
   
    @PutMapping("/user/booking/changestatus/{bookingId}")
	public ResponseEntity<?> updateBookingStatus(@PathVariable Long bookingId,@RequestParam String bookingStatus){
		return new ResponseEntity<>(bookingService.updateBookingStatus(bookingId,bookingStatus ),HttpStatus.OK);
	}
}
