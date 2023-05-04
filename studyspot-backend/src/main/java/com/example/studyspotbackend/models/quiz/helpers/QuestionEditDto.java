package com.example.studyspotbackend.models.quiz.helpers;

import lombok.Getter;

@Getter
public class QuestionEditDto {
    Long id;
    String questionText;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    int correctAnswer;
}
