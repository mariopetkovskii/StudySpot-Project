package com.example.studyspotbackend.repository.quiz;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultsRepository extends JpaRepository<QuizResults, Long> {
    QuizResults findQuizResultsByUserId(Long userId);
    QuizResults findByUserAndCourse(User user, Course course);
}
