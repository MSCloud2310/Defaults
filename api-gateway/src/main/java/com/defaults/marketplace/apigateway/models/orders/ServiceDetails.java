package com.defaults.marketplace.apigateway.models.orders;


public class ServiceDetails {

    private String category;
    private String title;
    private String description;
    private String destination;

    public ServiceDetails(String category, String title, String description, String destination) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.destination = destination;
    }

    public ServiceDetails() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
