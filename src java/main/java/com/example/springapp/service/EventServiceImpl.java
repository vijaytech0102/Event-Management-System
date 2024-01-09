package com.example.springapp.service;

import java.util.List;
import java.util.Optional;



import com.example.springapp.exceptions.EventAlreadyExistException;
import com.example.springapp.exceptions.NoBodyFoundException;
import com.example.springapp.model.Event;
import com.example.springapp.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository=eventRepository;
    }
    public Event createEvent(Event event)throws Exception{
            eventRepository.save(event);
            return event;
    }
    
   
   
    
    
    public boolean isNewEventValid(Event newEvent){
        List<Event> existingEvents = eventRepository.findAll();
        for(Event existEvent:existingEvents){
            
            System.out.println(existEvent);
          
        }
        for(Event existEvent:existingEvents){
            
            if(areEventsSame(existEvent,newEvent)){
                return false;
            }
          
        }
        return true;
    }
    public boolean areEventsSame(Event event1,Event event2){
        System.out.println("#######################################################");
        System.out.println(event1);
        System.out.println(event2);

        boolean same = event1.getEventType().equals(event2.getEventType())&&
        event1.getDescription().equals(event2.getDescription())&&
        event1.getEventCharges().equals(event2.getEventCharges())&&
        event1.getParticipantsCount().equals(event2.getParticipantsCount());
        System.out.println("*****************************************************");
       System.out.println(same);
        return same;
    }


	public List<Event> getEvents(){
        return eventRepository.findAll();
    }
    
    public boolean deleteEvent(Long eventId){
        if(eventRepository.existsById(eventId)){
            eventRepository.deleteById(eventId);
            return true;
        }
        return false;
    }

	public boolean updateEvent(Long eventId,Event event){
        if(eventRepository.existsById(eventId)) {
            Optional<Event> optionalEvent = eventRepository.findById(eventId);
            if(optionalEvent.isPresent()) {
                Event existEvent=optionalEvent.get();
                existEvent.setEventType(event.getEventType());
                existEvent.setDescription(event.getDescription());
                existEvent.setEventCharges(event.getEventCharges());
                existEvent.setParticipantsCount(event.getParticipantsCount());
                eventRepository.save(existEvent);
                return true;
            }
            return true;
                
            }
            return false;
    }
    public Event findEventByType(String eventType){
        Event event = eventRepository.findByEventType(eventType);
        if(event!=null){
            return event;
        }
        return null;
    }
    
}
