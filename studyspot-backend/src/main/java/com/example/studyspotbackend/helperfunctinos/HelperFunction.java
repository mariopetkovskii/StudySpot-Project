package com.example.studyspotbackend.helperfunctinos;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.exceptions.CourseNotFoundException;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.quiz.QuizResultsRepository;
import lombok.AllArgsConstructor;

import static com.example.studyspotbackend.security.SecurityConstants.SECRET;

public class HelperFunction {
    public static String decodeJwtToGetEmail(String jwtToken){
        String token = jwtToken.substring(7); // remove "Bearer " prefix
        String email = null;


        try {
            email = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();;
        } catch (JWTVerificationException e) {
            // handle verification exception
        }
        return email;
    }
}
