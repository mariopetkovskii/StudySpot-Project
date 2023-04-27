package com.example.studyspotbackend.models.user.exceptions;

public class UserNotExistsException extends RuntimeException{
    public UserNotExistsException() {
        super("User not exists");
    }
}