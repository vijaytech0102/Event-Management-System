package com.example.springapp.controller;


import com.example.springapp.model.Organizer;
import com.example.springapp.repository.OrganizerRepository;
import com.example.springapp.service.OrganizerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/registration/")
public class OrganizerController {
    private final OrganizerService organizerService;
    
    public OrganizerController(OrganizerService organizerService){
        this.organizerService=organizerService;
    }
    @PostMapping("/organizer")
	public ResponseEntity<?> createOrganizer( @RequestBody Organizer organizer)throws Exception{
		        return new ResponseEntity<>(organizerService.createOrganizer(organizer),HttpStatus.OK);
        
    }
   
   
    @GetMapping("/organizer")
	public ResponseEntity<?> getOrganizers(){
		return new ResponseEntity<>(organizerService.getOrganizers(),HttpStatus.OK);
	}
   
   
   
    @GetMapping("/organizer/organizerId/{organizerId}")
    public ResponseEntity<?> getOrganizer(@PathVariable Long organizerId){
		return new ResponseEntity<>(organizerService.getOrganizer(organizerId),HttpStatus.OK);
	}
}
