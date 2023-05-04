package com.example.studyspotbackend.service.quiz.impl;


import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.quiz.helpers.QuizResultsDto;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.quiz.QuizResultsRepository;
import com.example.studyspotbackend.service.quiz.interfaces.QuizResultsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizResultsServiceImpl implements QuizResultsService {
    private final QuizResultsRepository quizResultsRepository;

    @Override
    public Optional<QuizResults> quizResults(User user, Course course, Integer points) {
        QuizResults quizResults = this.quizResultsRepository.findByUserAndCourse(user, course);
        if(quizResults != null){
            quizResults.setPoints(points);
            return Optional.of(this.quizResultsRepository.save(quizResults));
        }
        return Optional.of(this.quizResultsRepository.save(
                new QuizResults(
                        user,
                        course,
                        points)));
    }

    @Override
    public Optional<QuizResults> getUserCoursePoints(QuizResultsDto quizResultsDto) {
        return Optional.of(this.quizResultsRepository.findQuizResultsByUserId(quizResultsDto.getUserId()));
    }

    @Override
    public Boolean canGenerateCert(User user, Course course) {
        QuizResults quizResults = this.quizResultsRepository.findByUserAndCourse(user, course);
        return quizResults.getPoints() > 2;
    }
}
