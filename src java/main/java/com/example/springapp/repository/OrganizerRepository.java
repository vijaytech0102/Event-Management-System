package com.example.springapp.repository;

import java.util.List;

import com.example.springapp.model.Organizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer,Long>{
    // List<Organizer> findByUserUserName(String userName);
    Organizer findByUserName(String userName);
    boolean existsByUserName(String userName);
    
}
