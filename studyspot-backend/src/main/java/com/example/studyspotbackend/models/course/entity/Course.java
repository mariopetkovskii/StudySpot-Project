package com.example.studyspotbackend.models.course.entity;

import com.example.studyspotbackend.models.quiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private List<Lesson> lessons;

    @OneToMany
    private List<Quiz> quizzes;

    public Course(String name, List<Lesson> lessons, List<Quiz> quizzes) {
        this.name = name;
        this.lessons = lessons;
        this.quizzes = quizzes;
    }
}
