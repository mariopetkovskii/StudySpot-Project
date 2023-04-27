package com.example.studyspotbackend.models.user.exceptions;

public class AccessForbiddenException extends RuntimeException{
    public AccessForbiddenException() {
        super("Access forbidden");
    }
}