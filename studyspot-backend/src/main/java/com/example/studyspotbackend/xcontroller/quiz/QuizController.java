package com.example.studyspotbackend.xcontroller.quiz;

import com.example.studyspotbackend.service.quiz.interfaces.QuizResultsService;

public class QuizController {
    private final QuizResultsService quizService;

    public QuizController(QuizResultsService quizService) {
        this.quizService = quizService;
    }
}
