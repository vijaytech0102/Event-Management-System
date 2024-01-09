package com.example.springapp.repository;

import java.util.Date;

import com.example.springapp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long>{
    Booking findByEventDate(Date eventDate);
    boolean existsByEventDate(Date eventDate);
    boolean existsByEvent_EventIdAndEventDateNot(Long eventId, Date date);
	
	

}