package com.example.studyspotbackend.models.user.exceptions;

public class PasswordDoNotMatchException extends RuntimeException{
    public PasswordDoNotMatchException() {
        super("Password do not match!");
    }
}