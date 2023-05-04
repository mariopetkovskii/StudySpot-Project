package com.example.studyspotbackend.models.quiz.helpers;

import lombok.Getter;

@Getter
public class QuestionDto {
    String questionText;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Integer correctAnswer;
    Long courseId;
}
