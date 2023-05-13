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
    private String name_mk;
    private String imageUrl;
    private String description;
    private String description_mk;

    @OneToMany
    private List<Lesson> lessons;

    @Column(name = "quiz_questions")
    @OneToMany
    private List<Question> quizQuestionsForThisCourse;

    public Course(String name, String imageUrl, String description, String name_mk, String description_mk) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.name = name;
        this.name_mk = name_mk;
        this.description_mk = description_mk;
        this.lessons = new ArrayList<>();
        this.quizQuestionsForThisCourse = new ArrayList<>();
    }
}
