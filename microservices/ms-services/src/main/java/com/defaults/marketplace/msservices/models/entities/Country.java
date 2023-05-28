package com.defaults.marketplace.msservices.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    private String name;
    private List<String> capital;
    private String region;
    private Map<String, String> languages;
    private Long population;
    private Map<String, Currency> currencies;
    private Map<String, String> flags;

    public Country() {
    }

    public Country(String name, List<String> capital, String region, Map<String, String> languages, Long population, Map<String, Currency> currencies, Map<String, String> flags) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.languages = languages;
        this.population = population;
        this.currencies = currencies;
        this.flags = flags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    public Map<String, String> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, String> flags) {
        this.flags = flags;
    }
}