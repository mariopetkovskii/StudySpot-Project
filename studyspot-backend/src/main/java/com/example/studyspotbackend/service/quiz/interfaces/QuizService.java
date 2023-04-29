package com.example.studyspotbackend.service.quiz.interfaces;

import com.example.studyspotbackend.models.quiz.entity.Quiz;
import com.example.studyspotbackend.models.quiz.helpers.QuizDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizEditDto;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    List<Quiz> findAll();

    Optional<Quiz> add(QuizDto quizDto);

    Optional<Quiz> edit(QuizEditDto quizEditDto);

    Optional<Quiz> findById(Long id);

    void deleteById(QuizEditDto quizEditDto);
}
