package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Payment;


public interface PaymentService {
    public Payment createPayment(Long bookingId,Payment payment);
    public List<Payment> getPayments();
    //public double processPayment(Booking booking);
	
    boolean updatePayment(Long bookingId);
    
 
}
