package com.example.studyspotbackend.models.course.entity;

import com.example.studyspotbackend.models.quiz.entity.Question;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @Column(name = "quiz_questions")
    @OneToMany
    private List<Question> quizQuestionsForThisCourse;

    public Course(String name) {
        this.name = name;
        this.lessons = new ArrayList<>();
        this.quizQuestionsForThisCourse = new ArrayList<>();
    }
}
