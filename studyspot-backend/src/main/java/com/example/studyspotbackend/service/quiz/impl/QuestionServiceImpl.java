package com.example.studyspotbackend.service.quiz.impl;

import com.example.studyspotbackend.models.quiz.entity.Question;
import com.example.studyspotbackend.models.quiz.helpers.QuestionDto;
import com.example.studyspotbackend.models.quiz.helpers.QuestionEditDto;
import com.example.studyspotbackend.repository.quiz.QuestionRepository;
import com.example.studyspotbackend.service.quiz.interfaces.QuestionService;
import org.springframework.stereotype.Service;
import com.example.studyspotbackend.models.quiz.exceptions.QuestionNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    @Override
    public Optional<Question> add(QuestionDto questionDto) {
        return Optional.of(this.questionRepository.save(new Question(
                questionDto.getQuestionText(), questionDto.getAnswer1(),
                questionDto.getAnswer2(), questionDto.getAnswer3(),
                questionDto.getAnswer4(), questionDto.getCorrectAnswer())));
    }

    @Override
    public Optional<Question> edit(QuestionEditDto questionEditDto) {
        Question question = this.questionRepository.findById(questionEditDto.getId()).
                orElseThrow(QuestionNotFoundException::new);
        question.setQuestionText(questionEditDto.getQuestionText());
        question.setAnswer1(questionEditDto.getAnswer1());
        question.setAnswer2(questionEditDto.getAnswer2());
        question.setAnswer3(questionEditDto.getAnswer3());
        question.setAnswer4(questionEditDto.getAnswer4());
        question.setCorrectAnswer(questionEditDto.getCorrectAnswer());
        return Optional.of(this.questionRepository.save(question));
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.of(this.questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new));
    }

    @Override
    public void deleteById(QuestionEditDto questionEditDto) {
        this.questionRepository.deleteById(questionEditDto.getId());
    }
}
