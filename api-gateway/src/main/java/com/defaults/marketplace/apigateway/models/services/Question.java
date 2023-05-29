package com.defaults.marketplace.apigateway.models.services;

import java.time.LocalDate;

public class Question {

    private Integer id;

    private LocalDate date;

    private String question;

    private Boolean solved;

    private String answer;

    private Integer serviceId;

    public Question() {
    }

    public Question(Integer id, LocalDate date, String question, Boolean solved, String answer, Integer serviceId) {
        this.id = id;
        this.date = date;
        this.question = question;
        this.solved = solved;
        this.answer = answer;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
