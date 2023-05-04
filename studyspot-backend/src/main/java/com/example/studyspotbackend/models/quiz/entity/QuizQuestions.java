package com.example.studyspotbackend.models.quiz.entity;

import lombok.Data;

@Data
public class QuizQuestions {
    private Long id;
    private String questionText;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;
}
