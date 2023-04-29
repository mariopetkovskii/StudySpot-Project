package com.example.studyspotbackend.xcontroller.course;

import com.example.studyspotbackend.service.course.interfaces.CourseService;

public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

}
