package com.example.studyspotbackend.repository.quiz;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResultsRepository extends JpaRepository<QuizResults, Long> {
    QuizResults findQuizResultsByUserId(Long userId);
    QuizResults findQuizResultsByUserAndCourse(User user, Course course);
}
