package com.example.studyspotbackend.models.course.exceptions;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException() {
        super("Lesson was not found");
    }
}
