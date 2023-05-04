package com.example.studyspotbackend.service.course.impl;

import com.example.studyspotbackend.helperfunctinos.HelperFunction;
import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.entity.Lesson;
import com.example.studyspotbackend.models.course.exceptions.CourseNotFoundException;
import com.example.studyspotbackend.models.course.helpers.CourseDto;
import com.example.studyspotbackend.models.course.helpers.CourseEditDto;
import com.example.studyspotbackend.models.course.helpers.LessonAndCourseDto;
import com.example.studyspotbackend.models.course.helpers.LessonDto;
import com.example.studyspotbackend.models.quiz.entity.Question;
import com.example.studyspotbackend.models.quiz.entity.QuizQuestions;
import com.example.studyspotbackend.models.quiz.entity.QuizResults;
import com.example.studyspotbackend.models.quiz.helpers.QuestionDto;
import com.example.studyspotbackend.models.quiz.helpers.QuizAnswerDto;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.course.CourseRepository;
import com.example.studyspotbackend.repository.user.UserRepository;
import com.example.studyspotbackend.service.course.interfaces.CourseService;
import com.example.studyspotbackend.service.course.interfaces.LessonService;
import com.example.studyspotbackend.service.quiz.interfaces.QuestionService;
import com.example.studyspotbackend.service.quiz.interfaces.QuizResultsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final LessonService lessonService;
    private final QuestionService questionService;
    private final QuizResultsService quizResultsService;
    private final UserRepository userRepository;

    @Override
    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    @Override
    public Optional<Course> add(CourseDto courseDto) {
        return Optional.of(this.courseRepository.save(new Course(courseDto.getName())));
    }

    @Override
    public Optional<Course> edit(CourseEditDto courseEditDto) {
        Course course = this.courseRepository.findById(courseEditDto.getId()).
                orElseThrow(CourseNotFoundException::new);
        course.setName((courseEditDto.getName()));
        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.of(this.courseRepository.findById(id).orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public void deleteById(CourseDto courseDto) {
        this.courseRepository.deleteById(courseDto.getId());
    }

    @Override
    public Optional<Course> addLessonToCourse(LessonAndCourseDto lessonAndCourseIdDto) {
        LessonDto lessonDto = new LessonDto();
        lessonDto.setDesc(lessonAndCourseIdDto.getDesc());
        lessonDto.setUrl(lessonAndCourseIdDto.getUrl());
        lessonDto.setName(lessonAndCourseIdDto.getName());

        Lesson lesson = this.lessonService.add(lessonDto);
        Course course = this.courseRepository.findById(lessonAndCourseIdDto.getCourseId()).
                orElseThrow(CourseNotFoundException::new);
        course.getLessons().add(lesson);
        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public Optional<Course> addQuizQuestionToCourse(QuestionDto questionDto) {
        Question question = this.questionService.add(questionDto);

        Course course = this.courseRepository.findById(questionDto.getCourseId()).
                orElseThrow(CourseNotFoundException::new);

        course.getQuizQuestionsForThisCourse().add(question);
        return Optional.of(this.courseRepository.save(course));
    }

    @Override
    public List<QuizQuestions> getCourseQuizQuestions(CourseDto courseDto) {
        Course course = this.courseRepository.findById(courseDto.getId()).orElseThrow(CourseNotFoundException::new);
        List<Question> questions = course.getQuizQuestionsForThisCourse();
        List<QuizQuestions> randomQuestions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++){
            Integer num = random.nextInt(questions.size());

            Question question = questions.get(num);

            QuizQuestions quizQuestion = new QuizQuestions();

            quizQuestion.setQuestionText(question.getQuestionText());
            quizQuestion.setAnswer1(question.getAnswer1());
            quizQuestion.setAnswer2(question.getAnswer2());
            quizQuestion.setAnswer3(question.getAnswer3());
            quizQuestion.setAnswer4(question.getAnswer4());
            quizQuestion.setId(question.getId());

            randomQuestions.add(quizQuestion);
            questions.remove(question);
        }
        return randomQuestions;
    }

    @Override
    public Optional<QuizResults> getResult(QuizAnswerDto quizAnswerDto, String jwtToken) {
        User user = this.userRepository.findByEmail(HelperFunction.decodeJwtToGetEmail(jwtToken));
        Course course = this.courseRepository.findById(quizAnswerDto.getCourseId()).orElseThrow(CourseNotFoundException::new);
        int correct = 0;
        for(int i = 0; i < quizAnswerDto.getQuestions().size(); i++){
            Question question = this.questionService.findById(quizAnswerDto.getQuestions().get(i).getQuestionId());
            if(question.getCorrectAnswer().equals(quizAnswerDto.getQuestions().get(i).getAnswer()))
                correct++;
        }
        return this.quizResultsService.quizResults(user, course, correct);
    }

}
