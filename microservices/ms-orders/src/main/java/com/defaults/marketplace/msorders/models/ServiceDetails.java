package com.defaults.marketplace.msorders.models;

import com.mongodb.lang.Nullable;

import java.util.List;

public class ServiceDetails {

    private String category;
    private String title;
    private @Nullable String description;

    private String destination;



    public ServiceDetails(String category, String title, String description) {
        this.category = category;
        this.title = title;
        this.description = description;
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

    public @Nullable String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
