package com.example.studyspotbackend.service.quiz.interfaces;

import com.example.studyspotbackend.models.quiz.entity.Question;
import com.example.studyspotbackend.models.quiz.helpers.QuestionDto;
import com.example.studyspotbackend.models.quiz.helpers.QuestionEditDto;

import java.util.List;
import java.util.Optional;


public interface QuestionService {
    List<Question> findAll();

    Question add(QuestionDto addQuestionDto);

    Optional<Question> edit(QuestionEditDto questionEditDto);

    Question findById(Long id);

    void deleteById(QuestionEditDto questionEditDto);
}
