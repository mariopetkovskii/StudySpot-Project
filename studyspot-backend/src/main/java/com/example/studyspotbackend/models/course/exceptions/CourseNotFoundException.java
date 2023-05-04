package com.example.studyspotbackend.models.course.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Course was not found");
    }
}
