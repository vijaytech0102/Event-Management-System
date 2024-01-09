package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Booking;
import com.example.springapp.model.Organizer;
import com.example.springapp.model.Payment;

import com.example.springapp.repository.BookingRepository;
import com.example.springapp.repository.OrganizerRepository;
import com.example.springapp.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService
{
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final OrganizerRepository organizerRepository;
    @Autowired
    public PaymentServiceImpl(BookingRepository bookingRepository,PaymentRepository paymentRepository,OrganizerRepository organizerRepository){
        this.bookingRepository=bookingRepository;
        this.paymentRepository=paymentRepository;
        this.organizerRepository=organizerRepository;

    }

    public Payment createPayment(Long bookingId,Payment payment) {
        Booking b=bookingRepository.findById(bookingId).orElseThrow(null);
        // b.getOrganizer().getOrganizerId();
 
        b.setPayment(payment);
        // b.setBookingStatus(b.getBookingStatus());
        payment.setBooking(b);
        
    //     double amount=0.0;
    //     String bookingStatus = b.getBookingStatus();
    //     if (bookingStatus.equals("Verified") || bookingStatus.equals("verified")) {
    //        amount=b.getAmount() * 0.4;
    //    }else {
    //     if(bookingStatus.equals("Confirmed") || bookingStatus.equals("confirmed"))
    //         amount=b.getAmount()*0.6;
    //    }
        return paymentRepository.save(payment);
    }
 
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
    public boolean updatePayment(Long bookingId){
        if(bookingRepository.existsById(bookingId)){
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(null);
            Payment payment=booking.getPayment();
            
            payment.setAmount(booking.getAmount());
            paymentRepository.save(payment);
            return true;
        }
        else{
            return false;
        }

    }

    
    
 
 
 
 
//    public double processPayment(Booking booking) {
//      double amount=0.0;
//      String bookingStatus = booking.getBookingStatus();
//        if (bookingStatus.equals("Verified")) {
//            amount=booking.getAmount() * 0.4;
//            
//            booking.setBookingStatus("Booked");  
//        }else {
//          if(bookingStatus.equals("Booked"))
//              amount=booking.getAmount()*0.6;
//        }
//        return amount;
//    }
}
    

