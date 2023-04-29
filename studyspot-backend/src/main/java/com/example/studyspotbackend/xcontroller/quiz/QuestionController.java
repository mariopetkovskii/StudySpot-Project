package com.example.studyspotbackend.xcontroller.quiz;

import com.example.studyspotbackend.service.quiz.interfaces.QuestionService;

public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

}
