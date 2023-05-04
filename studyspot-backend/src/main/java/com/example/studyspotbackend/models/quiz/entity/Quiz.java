package com.example.studyspotbackend.models.quiz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Quiz {
    private List<Question> questions;
}
