package com.example.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.springapp.exceptions.UserExistsException;

import com.example.springapp.exceptions.UserNotFoundException;

import com.example.springapp.model.AuthUser;
import com.example.springapp.model.Organizer;
import com.example.springapp.model.User;

import com.example.springapp.repository.HelloRepository;
import com.example.springapp.repository.OrganizerRepository;
import com.example.springapp.util.JwtTokenUtil;
import java.util.*;
@Service
@PreAuthorize("hasAnyRole('ROLE_USER')")
public class AuthService {

    @Autowired
    private HelloRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    HelloRepository auRepository;

    @Autowired
    OrganizerRepository organizerRepository;

    public Boolean signup(User user) throws Exception {
        // Organizer organizer = new Organizer();
        // if (user.getUserRole().equalsIgnoreCase("organizer")) {
        //     organizer.setUserName(user.getUsername());
        //     organizerRepository.save(organizer);
        // }
        // User tmpUser = repo.findByEmail(user.getEmail()); // Updated method name
        // if (tmpUser != null) {
        //     throw new UserExistsException("User with email " + user.getEmail() + " already exists");
        // }
        String encryptedPassword = encoder.encode(user.getPassword());
        // String token = jwtTokenUtil.createToken(user);
        user.setPassword(encryptedPassword);
        if(repo.save(user)!=null)
        {
            return true;

        }

        else
        {
            return false;

        }
        
        // AuthUser authUser = new AuthUser(user.getEmail(), user.getUsername(),user.getUserRole(),token);
        // System.out.println("Registration user details: " + authUser);
        // if(authUser!=null)
        // {
        //     return true;
        // }
        // else
        // {
        //     return false;
        // }
   
    }

    public Map<String, String> signin(User user) throws Exception {
        System.out.println("In service signin");
        User tmpUser = repo.findByEmail(user.getEmail()); // Updated method name
        if (tmpUser == null) {
            throw new UserNotFoundException("User with email " + user.getEmail() + " does not exist");
        }

        if (!encoder.matches(user.getPassword(), tmpUser.getPassword())) {
            throw new UserNotFoundException("Invalid email/password");
        }

        String token = jwtTokenUtil.createToken(user);

        // AuthUser authUser = new AuthUser(tmpUser.getUserId(), tmpUser.getEmail(),tmpUser.getUsername(),token,tmpUser.getUserRole());

        // System.out.println(authUser);
        Map<String, String> mp= new HashMap<>();
       
        mp.put("token",token);
        mp.put("userRole",tmpUser.getUserRole());
        return  mp;
    }
}
