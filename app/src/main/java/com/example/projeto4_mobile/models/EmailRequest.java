package com.example.projeto4_mobile.models;

public class EmailRequest {
    private String message;

    public EmailRequest() { }

    public EmailRequest(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}