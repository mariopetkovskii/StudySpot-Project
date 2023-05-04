package com.example.studyspotbackend.xcontroller.course;

import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.helpers.CourseDto;
import com.example.studyspotbackend.models.course.helpers.LessonAndCourseDto;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.quiz.entity.Quiz;
import com.example.studyspotbackend.models.quiz.entity.QuizQuestions;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.quiz.helpers.QuestionDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizAnswerDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizResultsDto;
import com.example.studyspotbackend.service.course.interfaces.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/course")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/add")
    private ResponseEntity<Course> addCourse(@RequestBody CourseDto courseDto){
        return this.courseService.add(courseDto)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete")
    private void deleteCourse(@RequestBody CourseDto courseDto){
        this.courseService.deleteById(courseDto);
    }

    @PostMapping("/lesson-add")
    private ResponseEntity<Course> addLessonToCourse(@RequestBody LessonAndCourseDto lessonAndCourseDto){
        return this.courseService.addLessonToCourse(lessonAndCourseDto)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/quiz-question-add")
    private ResponseEntity<Course> addQuizQuestionToCourse(@RequestBody QuestionDto questionDto){
        return this.courseService.addQuizQuestionToCourse(questionDto)
                .map(course -> ResponseEntity.ok().body(course))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get-quiz")
    private List<QuizQuestions> getQuizQuestions(@RequestBody CourseDto courseDto){
        return this.courseService.getCourseQuizQuestions(courseDto);
    }

    @PostMapping("/send-answers")
    private ResponseEntity<QuizResults> getQuizPoints(@RequestBody QuizAnswerDto quizAnswerDto,
                                                      @RequestHeader("Authorization") String authorizationHeader){
        return this.courseService.getResult(quizAnswerDto, authorizationHeader)
                .map(quizResults -> ResponseEntity.ok().body(quizResults))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
