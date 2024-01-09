package com.example.springapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;


import com.example.springapp.exceptions.EventBookedException;
import com.example.springapp.model.Booking;
import com.example.springapp.model.Event;
import com.example.springapp.model.Facility;
import com.example.springapp.model.Organizer;

import com.example.springapp.repository.BookingRepository;
import com.example.springapp.repository.EventRepository;
import com.example.springapp.repository.FacilityRepository;
import com.example.springapp.repository.OrganizerRepository;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final FacilityRepository facilityRepository;
   
    @PersistenceContext
    private EntityManager entityManager;

    public BookingServiceImpl(BookingRepository bookingRepository, EventRepository eventRepository,
            OrganizerRepository organizerRepository, FacilityRepository facilityRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
        this.facilityRepository = facilityRepository;
    
    }
    public Booking createBooking(BookingServiceRequest bookingRequest) throws Exception {

        System.out.println("bookingRequest++++++++++++++++++++++++++++++++++++++++++++++++++");

        // System.out.println(findOrganizersByUserUserName(bookingRequest.getUserName()));
        Booking booking =new Booking();
        booking.setAmount(bookingRequest.getAmount());
        booking.setBookingStatus(bookingRequest.getBookingStatus());
        
        booking.setDescription(bookingRequest.getDescription());
        booking.setHeadcount(bookingRequest.getHeadCount());
        booking.setEventDate(bookingRequest.getEventDate());
        booking.setSubmissionDate(bookingRequest.getSubmissionDate());

        Event event = eventRepository.findById(bookingRequest.getEventId()).orElse(null);
        Organizer organizer= organizerRepository.findByUserName(bookingRequest.getUserName()); 
        // Organizer organizer = organizerRepository.findById(bookingRequest.getOrganizerId()).orElse(null);
        List<Long> facilityIdlist = bookingRequest.getFacilitiesId();
        if(facilityIdlist.isEmpty()){
        
            List<Facility> facilities = facilityRepository.findAllById(facilityIdlist);
            event.getFacilities().addAll(facilities);

        }
        // bookingRepository.save(booking);
        // Booking existBooking =
        // bookingRepository.findByEventDate(booking.getEventDate());
        boolean existBooking = bookingRepository.existsByEventDate(booking.getEventDate());
        System.out.println(existBooking + "**********************************************");
        if (existBooking) {
            Event oldEvent = bookingRepository.findByEventDate(booking.getEventDate()).getEvent();

            System.out.println("old id" + oldEvent.getEventId() + "  new Id" + bookingRequest.getEventId());
            if (oldEvent.getEventId() == bookingRequest.getEventId()) {
                throw new EventBookedException("OOPS ! Sorry this event is booked.");

            }
            if(event!=null &&organizer!=null) {
            	booking.setEvent(event);
            	booking.setOrganizer(organizer);
            	
            	event.getBookings().add(booking);
            	organizer.getBookings().add(booking);
            	List<Event> organizerEvents= organizer.getEvents();
            	organizerEvents.add(event);
            	organizer.setEvents(organizerEvents);
                // eventRepository.save(event);
            	List<Organizer> eventOrganizer=event.getOrganizers();
            	eventOrganizer.add(organizer);
            	organizer.setEvents(organizerEvents);
                System.out.println("99999999999999999999999999999999999999900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009");
                // System.out.println(organizer);
            	// organizerRepository.save(organizer);
            //    System.out.println(organizer);
            	
            	return bookingRepository.save(booking);
            }

        }
        if(event!=null &&organizer!=null) {
        	booking.setEvent(event);
            	booking.setOrganizer(organizer);
            	
            	event.getBookings().add(booking);
            	organizer.getBookings().add(booking);
            	List<Event> organizerEvents= organizer.getEvents();
            	organizerEvents.add(event);
            	organizer.setEvents(organizerEvents);
                // eventRepository.save(event);
            	List<Organizer> eventOrganizer=event.getOrganizers();
            	eventOrganizer.add(organizer);
            	organizer.setEvents(organizerEvents);
                System.out.println("99999999999999999999999999999999999999900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000009");
            	
            	return bookingRepository.save(booking);
        }
        
		return booking;
       


    }
    // public Organizer findOrganizersByUserUserName(String userName){
        
       
    //     // List<Organizer> organizers = entityManager.createQuery("SELECT o FROM Organizer WHERE o.user.userName=:userName",Organizer.class).setParameter("userName",userName).getResultList();
       
    //    User user = userRepository.findByUserName(userName);
    //    if(user!=null){
    //     return organizerRepository.findByUserUserName(userName).get(0);
    //    }
    //    return null;

        
    // }



    public Booking findByBookingId(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            return bookingRepository.findById(bookingId).get();

        }
        return null;

    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public boolean deleteBooking(Long bookingId) {
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
            return true;
        }
        return false;
    }

    public boolean updateBooking(Long bookingId, Booking booking) {
        if (bookingRepository.existsById(bookingId)) {
            Booking newBooking = bookingRepository.findById(bookingId).orElse(null);
            newBooking.setAmount(booking.getAmount());
            newBooking.setBookingStatus(booking.getBookingStatus());
            newBooking.setDescription(booking.getDescription());
            newBooking.setEventDate(booking.getEventDate());
            newBooking.setSubmissionDate(booking.getSubmissionDate());
            newBooking.setHeadcount(booking.getHeadcount());
            bookingRepository.save(newBooking);
            return true;

        }
        return false;
    }

    public boolean updateBookingStatus(Long bookingId, String bookingStatus) {

        if (bookingRepository.existsById(bookingId)) {
            Booking newBooking = bookingRepository.findById(bookingId).orElse(null);

            newBooking.setBookingStatus(bookingStatus);
            bookingRepository.save(newBooking);

            return true;

        }
        return false;
    }

}
