package com.example.springapp.exceptionHandler;

public class EventAlreadyExistException extends Exception{

    public EventAlreadyExistException() {
        super();
    }

    public EventAlreadyExistException(String message) {
        super(message);
    }
    
    
    
}
