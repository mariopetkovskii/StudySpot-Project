package com.example.studyspotbackend.service.course.interfaces;

import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.course.helpers.LessonEditDto;

import java.util.List;
import java.util.Optional;
public interface LessonService {
    List<Lesson> findAll();

    Lesson add(LessonDto lessonDto);

    Optional<Lesson> edit(LessonEditDto lessonEditDto);

    Optional<Lesson> findById(Long id);

    void deleteById(LessonEditDto lessonEditDto);
}