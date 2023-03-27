package com.defaults.marketplace.msservices.repository;

import com.defaults.marketplace.msservices.models.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findQuestionById(Integer id);
    Question findQuestionByServiceId(Integer serviceId);

    List<Question> findAllByServiceId(Integer serviceId);
    List<Question> findAllBySolved(Boolean solved);
}
