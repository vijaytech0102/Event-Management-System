package com.example.springapp.controller;

import com.example.springapp.model.Event;
import com.example.springapp.service.EventService;

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
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin()
@RestController
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
   
   
   
    @Autowired
    public EventController(EventService eventService){
        this.eventService=eventService;
    }
   
   
    @PostMapping("/event") 
	public ResponseEntity<?> createEvent( @RequestBody Event event) throws Exception{
        
		return new ResponseEntity<>(eventService.createEvent(event),HttpStatus.OK);
	}

    @GetMapping("/event")
	public ResponseEntity<?> getEvents(){
		return new ResponseEntity<>(eventService.getEvents(),HttpStatus.OK);
	}

    @GetMapping("/user/event")
	public ResponseEntity<?> getEventUser(){
		return new ResponseEntity<>(eventService.getEvents(),HttpStatus.OK);
	}
	@DeleteMapping("/deleteEvent/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable("eventId") Long eventId){
		return new ResponseEntity<>(eventService.deleteEvent(eventId),HttpStatus.OK);
	}
	@PutMapping("/admin/updateevent/{eventId}")
	public ResponseEntity<?> updateEvent(@PathVariable Long eventId,@RequestBody Event event){
		return new ResponseEntity<>(eventService.updateEvent(eventId, event),HttpStatus.OK);
	}
    @GetMapping("/admin/event/{eventType}")
	public ResponseEntity<?> findEventByType(@PathVariable String eventType){
		return new ResponseEntity<>(eventService.findEventByType(eventType),HttpStatus.OK);
	}

}
