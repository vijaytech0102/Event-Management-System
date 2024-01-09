package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Facility;

public interface FacilityService {
    Facility createFacility(Facility facility)throws Exception;
    public List<Facility> getFacilities();
	boolean deleteFacility(Long facilityId);
    boolean updateFacility(Long facilityId, Facility facility);
}
