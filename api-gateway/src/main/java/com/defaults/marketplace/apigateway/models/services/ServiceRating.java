package com.defaults.marketplace.apigateway.models.services;

public class ServiceRating {

    Long id;
    private int rating;
    private String comment;
    private int userId;

    public ServiceRating() {
    }

    public ServiceRating(Long id, int rating, String comment, int userId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

        
}
