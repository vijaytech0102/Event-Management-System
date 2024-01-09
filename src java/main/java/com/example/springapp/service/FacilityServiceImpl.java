package com.example.springapp.service;

import java.util.List;

import com.example.springapp.exceptions.NoBodyFoundException;
import com.example.springapp.model.Facility;
import com.example.springapp.repository.FacilityRepository;

import org.springframework.stereotype.Service;

@Service
public class FacilityServiceImpl implements FacilityService  {
    private final FacilityRepository facilityRepository;
    public FacilityServiceImpl(FacilityRepository facilityRepository){
        this.facilityRepository=facilityRepository;
    }
    public Facility createFacility(Facility facility)throws Exception{
        if(facility!=null){
            facilityRepository.save(facility);
            return facility;
        }
        throw new NoBodyFoundException("Please Enter the Value Before Post");
    }
    public List<Facility> getFacilities(){
        return (List<Facility>)facilityRepository.findAll();
    }  
    public boolean deleteFacility(Long facilityId){
        if(facilityRepository.existsById(facilityId)){
            facilityRepository.deleteById(facilityId);
            return true;
        }
        return false;
    }
    
    
    public  boolean updateFacility(Long facilityId,Facility facility){
        if(facilityRepository.existsById(facilityId)){
            Facility oldFacility =facilityRepository.findById(facilityId).orElseThrow(null);
            oldFacility.setFacilityDescription(facility.getFacilityDescription());
            oldFacility.setPrice(facility.getPrice());
            facilityRepository.save(oldFacility);
            return true; 
        }
        return false;
    }

}
