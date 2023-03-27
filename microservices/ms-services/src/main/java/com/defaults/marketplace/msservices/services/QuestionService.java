package com.defaults.marketplace.msservices.services;

import com.defaults.marketplace.msservices.models.entities.Question;
import com.defaults.marketplace.msservices.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repository;

    public Question saveQuestion(Question question){
        return repository.save(question);
    }

    public List<Question> getQuestionsByServiceId(Integer serviceId){
        return repository.findAllByServiceId(serviceId);
    }

    public List<Question> getQuestionsBySolved(Boolean solved){
        return repository.findAllBySolved(solved);
    }

    public Question getQuestionById(Integer id, Integer serviceId){
        List<Question> questions = getQuestionsByServiceId(serviceId);
        Question question = null;
        for (Question q: questions){
            if (q.getId().equals(id)){
                question = q;
            }
        }
        return question;
    }

    public Question updateQuestion(Question newQuestion, Question existingQuestion){
        existingQuestion.setDate(newQuestion.getDate());
        existingQuestion.setQuestion(newQuestion.getQuestion());
        existingQuestion.setSolved(newQuestion.getSolved());
        existingQuestion.setAnswer(newQuestion.getAnswer());

        return repository.save(existingQuestion);
    }

    public Boolean deleteQuestion(Integer id, Integer serviceId){
        List<Question> questions = getQuestionsByServiceId(serviceId);
        for (Question q: questions){
            if (q.getId().equals(id)){
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}
