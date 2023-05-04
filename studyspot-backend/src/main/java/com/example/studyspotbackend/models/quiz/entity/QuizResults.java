package com.example.studyspotbackend.models.quiz.entity;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class QuizResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    @OneToOne
    @JsonIgnore
    private Course course;

    private Integer points;

    public QuizResults(User user, Course course, Integer points) {
        this.user = user;
        this.course = course;
        this.points = points;
    }
}
