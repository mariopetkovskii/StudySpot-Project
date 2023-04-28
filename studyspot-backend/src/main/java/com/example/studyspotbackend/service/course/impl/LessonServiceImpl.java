package com.example.studyspotbackend.service.course.impl;

import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.exceptions.LessonNotFoundException;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.course.helpers.LessonEditDto;
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

    @Override
    public List<Lesson> findAll() {
        return this.lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> add(LessonDto lessonDto) {
        return Optional.of(this.lessonRepository.save(new Lesson(lessonDto.getName(), lessonDto.getDesc(), lessonDto.getUrl())));
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
    public void deleteById(LessonEditDto lessonEditDto) {
        this.lessonRepository.deleteById(lessonEditDto.getId());
    }
}
