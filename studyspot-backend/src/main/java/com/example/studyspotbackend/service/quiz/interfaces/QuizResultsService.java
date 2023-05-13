package com.example.studyspotbackend.service.quiz.interfaces;

import com.example.studyspotbackend.QuizResultsHelperWithCourse;
import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.quiz.helpers.QuizDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizEditDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizResultsDto;
import com.example.studyspotbackend.models.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface QuizResultsService {
    Optional<QuizResults> quizResults(User user, Course course, Integer points);

    List<QuizResultsHelperWithCourse> getUserCoursePoints(User user);
    Boolean canGenerateCert(User user, Course course);
}
