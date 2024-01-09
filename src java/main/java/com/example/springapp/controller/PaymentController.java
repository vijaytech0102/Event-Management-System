package com.example.springapp.controller;

import com.example.springapp.model.Payment;
import com.example.springapp.repository.PaymentRepository;
import com.example.springapp.service.PaymentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
public class PaymentController {
    private final PaymentService paymentService;
    
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }
   
   
   
   
   
    @PostMapping("/user/payments/{bookingId}")
	public ResponseEntity<?> createPayment(@RequestBody Payment payment ,@PathVariable Long bookingId)throws Exception{
        
   
		return new ResponseEntity<>(paymentService.createPayment(bookingId, payment),HttpStatus.ACCEPTED);
	}
   
    @GetMapping("/user/payment")
	public ResponseEntity<?> getPayments(){
		return new ResponseEntity<>(paymentService.getPayments(),HttpStatus.OK);
	}
  
  
  
    
    @GetMapping("/user/payment/{bookingId}")
    public ResponseEntity<?> updatePayment(@PathVariable Long bookingId){
        return new ResponseEntity<>(paymentService.updatePayment(bookingId),HttpStatus.ACCEPTED);
    }
    

    
}
