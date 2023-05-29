package com.defaults.marketplace.apigateway.models.services;


import java.time.LocalDate;
import java.util.List;

public class ServiceC {

    private Integer id;

    private ServiceCategory category;

    private String title;

    private String description;

    private String countryCode;

    private Double cost;

    private Integer capacity;

    // ServiceTransportation and ServiceTour

    private String origin;

    private String destination;

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private String transportationType;

    // ServiceLodging

    private String location;

    private LocalDate checkinTime;

    private LocalDate checkoutTime;

    private Boolean breakfast;

    private Boolean internet;

    //ServiceMeal

    private ServiceMealType mealType;

    private Integer servings;

    private Boolean vegan;

    /* Relación con otras entidades*/

    private Integer providerId;

    private List<Question> questions;

    private List<ServiceRating> ratings;

    private List<Location> locations;

    public ServiceC() {
    }

    public ServiceC(Integer id, ServiceCategory category, String title, String description, String countryCode, Double cost, Integer capacity, String origin, String destination, LocalDate departureTime, LocalDate arrivalTime, String transportationType, String location, LocalDate checkinTime, LocalDate checkoutTime, Boolean breakfast, Boolean internet, ServiceMealType mealType, Integer servings, Boolean vegan, Integer providerId, List<Question> questions) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.description = description;
        this.countryCode = countryCode;
        this.cost = cost;
        this.capacity = capacity;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.transportationType = transportationType;
        this.location = location;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.breakfast = breakfast;
        this.internet = internet;
        this.mealType = mealType;
        this.servings = servings;
        this.vegan = vegan;
        this.providerId = providerId;
        this.questions = questions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDate checkinTime) {
        this.checkinTime = checkinTime;
    }

    public LocalDate getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDate checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    public ServiceMealType getMealType() {
        return mealType;
    }

    public void setMealType(ServiceMealType mealType) {
        this.mealType = mealType;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<ServiceRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<ServiceRating> ratings) {
        this.ratings = ratings;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


}
