package com.example.studyspotbackend.service.course.interfaces;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.helpers.CourseDto;
import com.example.studyspotbackend.models.course.helpers.CourseEditDto;
import com.example.studyspotbackend.models.course.helpers.LessonAndCourseDto;
import com.example.studyspotbackend.models.quiz.entity.QuizQuestions;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.quiz.helpers.QuestionDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizAnswerDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizResultsDto;
import com.example.studyspotbackend.models.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> findAll();

    Optional<Course> add(CourseDto courseDto);

    Optional<Course> edit(CourseEditDto courseEditDto);

    Optional<Course> findById(Long id);

    void deleteById(CourseDto courseDto);

    Optional<Course> addLessonToCourse(LessonAndCourseDto lessonAndCourseIdDto);

    Optional<Course> addQuizQuestionToCourse(QuestionDto questionDto);

    List<QuizQuestions> getCourseQuizQuestions(CourseDto courseDto);

    Optional<QuizResults> getResult(QuizAnswerDto quizAnswerDto, String jwtToken);
}
