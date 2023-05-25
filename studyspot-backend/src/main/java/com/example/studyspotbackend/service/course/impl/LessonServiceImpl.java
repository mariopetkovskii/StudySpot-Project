package com.example.studyspotbackend.service.course.impl;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.exceptions.LessonNotFoundException;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.course.helpers.LessonEditDto;
import com.example.studyspotbackend.repository.course.CourseRepository;
import com.example.studyspotbackend.repository.course.LessonRepository;
import com.example.studyspotbackend.service.course.interfaces.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Lesson add(LessonDto lessonDto) {
        return this.lessonRepository.save(new Lesson(lessonDto.getName(), lessonDto.getDesc(), lessonDto.getUrl(), lessonDto.getName_mk(), lessonDto.getDesc_mk()));
    }

    @Override
    public Optional<Lesson> edit(LessonEditDto lessonEditDto) {
        Lesson lesson = this.lessonRepository.findById(lessonEditDto.getId()).orElseThrow(LessonNotFoundException::new);
        lesson.setName(lessonEditDto.getName());
        lesson.setDescription(lessonEditDto.getDesc());
        lesson.setUrl(lessonEditDto.getUrl());
        return Optional.of(this.lessonRepository.save(lesson));
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return Optional.of(this.lessonRepository.findById(id).orElseThrow(LessonNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) {
        Lesson lesson = this.findById(id).orElseThrow(LessonNotFoundException::new);
        Course course = this.courseRepository.findCourseByLessons(lesson);
        course.getLessons().remove(lesson);
        this.courseRepository.save(course);
        this.lessonRepository.deleteById(id);
    }
}
