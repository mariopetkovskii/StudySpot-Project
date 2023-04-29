package com.example.studyspotbackend.xcontroller.quiz;

import com.example.studyspotbackend.service.quiz.interfaces.QuizService;

public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
}
