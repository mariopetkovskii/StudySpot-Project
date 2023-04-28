package com.example.studyspotbackend.repository.course;

import com.example.studyspotbackend.models.course.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}