package com.example.studyspotbackend.service.course.impl;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.exceptions.CourseNotFoundException;
import com.example.studyspotbackend.models.course.helpers.CourseEditDto;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import com.example.studyspotbackend.repository.course.CourseRepository;
import com.example.studyspotbackend.service.course.interfaces.CourseService;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> add(CourseEditDto courseEditDto) {
        return Optional.of(this.courseRepository.save(new Course(courseEditDto.getName(),
                                courseEditDto.getLessons(), courseEditDto.getQuizzes())));
    }

    @Override
    public Optional<Course> edit(CourseEditDto courseEditDto) {
        Course course = this.courseRepository.findById(courseEditDto.getId()).
                orElseThrow(CourseNotFoundException::new);
        course.setName((courseEditDto.getName()));
        course.setLessons(courseEditDto.getLessons());
        course.setQuizzes(courseEditDto.getQuizzes());
        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.of(this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public void deleteById(CourseEditDto courseEditDto) {
        this.courseRepository.deleteById(courseEditDto.getId());
    }

    @Override
    public List<Quiz> findCourseQuizzes(Long id) {
        return this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new).getQuizzes();
    }
}
