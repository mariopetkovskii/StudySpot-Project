package com.example.studyspotbackend.models.user.exceptions;

public class TokenDoesNotMatchUserException extends RuntimeException{
    public TokenDoesNotMatchUserException() {
        super("Token does not match user");
    }
}