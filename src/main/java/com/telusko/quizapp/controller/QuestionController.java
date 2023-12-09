package com.telusko.quizapp.controller;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionbyCategory(@PathVariable String category){
        return questionService.getQuestionbyCategory(category);

    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @GetMapping("difficultyLevel/{difficultyLevel}")
    public ResponseEntity<List <Question>> getQuestionByDifficultyLevel(@PathVariable String difficultyLevel) {
        return questionService.getQuestionByDifficultyLevel(difficultyLevel);
    }
}
