package com.example.studyspotbackend.service.quiz.impl;

import com.example.studyspotbackend.models.quiz.entity.Quiz;
import com.example.studyspotbackend.models.quiz.helpers.QuizDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizEditDto;
import com.example.studyspotbackend.repository.quiz.QuizRepository;
import com.example.studyspotbackend.service.quiz.interfaces.QuizService;
import com.example.studyspotbackend.models.quiz.exceptions.QuizNotFoundException;

import java.util.List;
import java.util.Optional;

public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Quiz> findAll() {
        return this.quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> add(QuizDto quizDto) {
        return Optional.of(this.quizRepository.save(new Quiz(quizDto.getQuestions())));
    }

    @Override
    public Optional<Quiz> edit(QuizEditDto quizEditDto) {
        Quiz quiz = this.quizRepository.findById(quizEditDto.getId()).
                orElseThrow(QuizNotFoundException::new);
        quiz.setQuestions(quizEditDto.getQuestions());
        return Optional.of(this.quizRepository.save(quiz));
    }

    @Override
    public Optional<Quiz> findById(Long id) {
        return Optional.of(this.quizRepository.findById(id).orElseThrow(QuizNotFoundException::new));
    }

    @Override
    public void deleteById(QuizEditDto quizEditDto) {
        this.quizRepository.deleteById(quizEditDto.getId());
    }
}
