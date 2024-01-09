package com.example.springapp.model;

public class AuthUser {

    private Long userId;
    private String email;
    private String username;
    private String token;
    private String role;

    // Default constructor
    public AuthUser() {
    }

    public AuthUser(Long userId, String email, String username, String token, String role) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.token = token;
        this.role = role;
    }

    public AuthUser(String email, String username, String token, String role) {
        this.email = email;
        this.username = username;
        this.token = token;
        this.role = role;
    }

    public Long getUserId() {
        
        return userId;
    }

    public void setUserId(Long userId) {
        
        this.userId = userId;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}