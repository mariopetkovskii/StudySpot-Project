package com.example.studyspotbackend.models.quiz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    @Column(name = "correct_answer")
    private int correctAnswer;

    public Question(String questionText, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
        this.questionText = questionText;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }
}