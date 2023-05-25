package com.example.studyspotbackend.xcontroller.course;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.exceptions.LessonNotFoundException;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.course.helpers.LessonEditDto;
import com.example.studyspotbackend.repository.course.CourseRepository;
import com.example.studyspotbackend.service.course.interfaces.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/lesson")
@AllArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/getAll")
    public List<Lesson> getAll(){
        return this.lessonService.findAll();
    }

    @PutMapping("/edit-lesson")
    public ResponseEntity<Lesson> editLesson(@RequestBody LessonEditDto lessonEditDto){
        return this.lessonService.edit(lessonEditDto)
                .map(lesson -> ResponseEntity.ok().body(lesson))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get-lesson/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id){
        return this.lessonService.findById(id)
                .map(lesson -> ResponseEntity.ok().body(lesson))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete-lesson/{id}")
    public ResponseEntity deleteLesson(@PathVariable Long id){
        this.lessonService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
