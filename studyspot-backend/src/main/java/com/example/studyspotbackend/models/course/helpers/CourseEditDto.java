package com.example.studyspotbackend.models.course.helpers;

import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseEditDto {
    Long id;
    String name;
    String nameMk;
    String imageUrl;
    List<Lesson> lessons;
    List<Quiz> quizzes;
}
