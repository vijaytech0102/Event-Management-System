package com.example.springapp.service;

import java.util.List;


import com.example.springapp.model.Organizer;
import com.example.springapp.repository.OrganizerRepository;

import org.springframework.stereotype.Service;



@Service
public class OrganizerServiceImpl implements OrganizerService{
    private final OrganizerRepository organizerRepository;
    public OrganizerServiceImpl(OrganizerRepository organizerRepository){
        this.organizerRepository=organizerRepository;
    }
    
  
  
    public Organizer createOrganizer(Organizer organizer)throws Exception{
        // if(organizer.getMobileNumber()!=null){
            return organizerRepository.save(organizer);
        // }
            //  throw new NoBodyFoundException("Please Enter the Value Before Post");
        
    }
    public List<Organizer> getOrganizers(){
        return organizerRepository.findAll();
        
    }
    public  Organizer getOrganizer(Long organizerId){
        if(organizerRepository.existsById(organizerId)){
            System.out.println(organizerRepository.findById(organizerId).get());
            return organizerRepository.findById(organizerId).get();
        }
        return null;
    }
    
}
