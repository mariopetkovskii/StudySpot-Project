package com.example.studyspotbackend.repository.course;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByLessons(Lesson lesson);
}
