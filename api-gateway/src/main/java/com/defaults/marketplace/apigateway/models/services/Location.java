package com.defaults.marketplace.apigateway.models.services;

public class Location {

    private Integer id;

    private String name;

    private String capital;

    private String currencies;

    private String languages;

    private String region;

    private String population;

    private String flag;

    private Integer serviceId;

    public Location() {
    }

    public Location(Integer id, String name, String capital, String currencies, String languages, String region, String population, String flag, Integer serviceId) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.currencies = currencies;
        this.languages = languages;
        this.region = region;
        this.population = population;
        this.flag = flag;
        this.serviceId = serviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    

}
