package com.example.springapp.controller;

import com.example.springapp.model.Facility;
import com.example.springapp.repository.FacilityRepository;
import com.example.springapp.service.FacilityService;

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
public class FacilityController {
    private final FacilityService facilityService;
    @Autowired
    public FacilityController(FacilityService facilityService){
        this.facilityService=facilityService;
    }
   
   
   
    
    
    
    
    
    
    
    
    
    
    
    
    

    @PostMapping("/facility")
    public ResponseEntity<?> createFacility(@RequestBody Facility facility)throws Exception{
        return new ResponseEntity<>(facilityService.createFacility(facility),HttpStatus.OK);
    }
    @GetMapping("/facility")
    public ResponseEntity<?> getFacilities(){
        return new ResponseEntity<>(facilityService.getFacilities(),HttpStatus.OK);
    }    
    @DeleteMapping("/deleteFacility/{facilityId}")
	public ResponseEntity<?> deleteEvent(@PathVariable("facilityId") Long facilityId){
		return new ResponseEntity<>(facilityService.deleteFacility(facilityId) ,HttpStatus.OK);
	}
	@PutMapping("/admin/updatefacility/{facilityId}")
	public ResponseEntity<?> updateEvent(@PathVariable Long facilityId,@RequestBody Facility facility){
		return new ResponseEntity<>(facilityService.updateFacility( facilityId,facility),HttpStatus.OK);
	}
}
