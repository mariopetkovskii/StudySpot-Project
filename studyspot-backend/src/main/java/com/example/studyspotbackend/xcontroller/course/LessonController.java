package com.example.studyspotbackend.xcontroller.course;

import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.course.helpers.LessonEditDto;
import com.example.studyspotbackend.service.course.interfaces.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add-lesson")
    public ResponseEntity<Lesson> addLesson(@RequestBody LessonDto lessonDto){
        return this.lessonService.add(lessonDto)
                .map(lesson -> ResponseEntity.ok().body(lesson))
                .orElseGet(() -> ResponseEntity.badRequest().build());
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

    @DeleteMapping("/delete-lesson")
    public void deleteLesson(@RequestBody LessonEditDto lessonEditDto){
        this.lessonService.deleteById(lessonEditDto);
    }
}
