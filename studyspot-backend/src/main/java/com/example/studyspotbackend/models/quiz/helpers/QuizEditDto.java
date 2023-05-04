package com.example.studyspotbackend.models.quiz.helpers;

import com.example.studyspotbackend.models.quiz.entity.Question;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizEditDto {
    Long id;
    List<Question> questions;
}
