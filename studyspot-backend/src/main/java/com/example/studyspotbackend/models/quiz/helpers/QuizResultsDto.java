package com.example.studyspotbackend.models.quiz.helpers;

import lombok.Getter;

@Getter
public class QuizResultsDto {
    private Long userId;

    private Long courseId;

    private Integer points;
}
