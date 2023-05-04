package com.example.studyspotbackend.models.quiz.helpers;

import lombok.Getter;

import java.util.List;

@Getter
public class QuizAnswerDto {
    private Long userId;
    private Long courseId;
    private List<QuizQuestionDto> questions;

}
