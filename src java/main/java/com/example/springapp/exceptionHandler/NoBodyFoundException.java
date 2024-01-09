package com.example.springapp.exceptionHandler;

public class NoBodyFoundException extends Exception{

    public NoBodyFoundException() {
        super();
    }

    public NoBodyFoundException(String message) {
        super(message);
    }
    
    
}
