package com.example.studyspotbackend.service.course.interfaces;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.helpers.CourseDto;
import com.example.studyspotbackend.models.course.helpers.CourseEditDto;
import com.example.studyspotbackend.models.quiz.entity.Quiz;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> add(CourseEditDto courseEditDto);

    Optional<Course> edit(CourseEditDto courseEditDto);

    Optional<Course> findById(Long id);

    void deleteById(CourseEditDto courseEditDto);

    List<Quiz> findCourseQuizzes(Long id);
}
