package com.example.springapp.exceptions;

public class UserExistsException extends Exception {
	public UserExistsException() {
		super();
	}
	public UserExistsException(String msg) {
		super(msg);
	}
}

