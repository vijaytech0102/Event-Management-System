package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Organizer;

public interface OrganizerService {
	Organizer createOrganizer(Organizer organizer)throws Exception;
	List<Organizer> getOrganizers();
	// boolean deleteOrganizer(Long organizerId) ;
	//  boolean updateOrganizer(Long organizerId,Organizer organizer);
    Organizer getOrganizer(Long organizerId);
}
