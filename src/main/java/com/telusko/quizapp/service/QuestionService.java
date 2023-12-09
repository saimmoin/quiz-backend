package com.telusko.quizapp.service;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }
    public ResponseEntity<List<Question>> getQuestionbyCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED) ;

    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (questionDao.existsById(id)) {
                questionDao.deleteById(id);
                return ResponseEntity.ok("Question Deleted Successfully");
            } else {
                return ResponseEntity.status(404).body("Question not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred during deletion");
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        try {
            Optional<Question> existingQuestionOptional = questionDao.findById(id);

            if (existingQuestionOptional.isPresent()) {
                Question existingQuestion = existingQuestionOptional.get();

                existingQuestion.setCorrectAnswer(updatedQuestion.getCorrectAnswer());
                questionDao.save(existingQuestion);

                return ResponseEntity.ok("Question Updated Successfully");
            } else {
                return ResponseEntity.status(404).body("Question not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occurred during update");
        }
    }

    public ResponseEntity<List <Question>> getQuestionByDifficultyLevel(String difficultyLevel) {
        try {
            return new ResponseEntity<>(questionDao.findByDifficultyLevel(difficultyLevel), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

}
