package com.example.springapp.service;

import java.util.List;

import com.example.springapp.model.Event;

public interface EventService {
    Event createEvent(Event event)throws Exception;
	List<Event> getEvents();
	boolean deleteEvent(Long eventId);
    boolean updateEvent(Long eventId,Event event);
    Event findEventByType(String eventType);
    boolean isNewEventValid(Event newEvent);
    boolean areEventsSame(Event event1,Event event2);
}
