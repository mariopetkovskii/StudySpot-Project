package com.example.studyspotbackend.models.quiz.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super("Question was not found");
    }
}