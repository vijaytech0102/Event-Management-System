package com.example.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.springapp.model.*;
import com.example.springapp.service.*;

import com.example.springapp.config.*;

// @CrossOrigin(SecurityConfig.MY_URL)
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService ser;
	
	
	@GetMapping("/foo")
	public String foo() {
		return "foo";
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	//@Secured("ROLE_USER")
	public ResponseEntity<?> signup(@RequestBody User user){
		System.out.println("In controller signup");
		try{
			return new ResponseEntity<>(ser.signup(user), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/login")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	//@Secured("ROLE_USER")
	public ResponseEntity<?> signin(@RequestBody User user){
		System.out.println("In controller signup");
		try
        {
			return new ResponseEntity<>(ser.signin(user), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

    
	@GetMapping("/signout")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	//@Secured("ROLE_USER")
	public void logout(){
		System.out.println("In controller signout");
		SecurityContextHolder.clearContext();
	}
}
