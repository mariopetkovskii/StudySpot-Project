package com.example.studyspotbackend.models.user.exceptions;

public class UserNotEnabledException extends RuntimeException{
    public UserNotEnabledException() {
        super(String.format("User not enabled!"));
    }
}