package com.example.studyspotbackend.models.quiz.exceptions;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Quiz was not found");
    }
}
