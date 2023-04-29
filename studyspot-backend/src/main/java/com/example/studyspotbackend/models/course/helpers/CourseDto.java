package com.example.studyspotbackend.models.course.helpers;

import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import lombok.Getter;

import java.util.List;

@Getter
public class CourseDto {
    String name;
    List<Lesson> lessons;
    List<Quiz> quizzes;
}
