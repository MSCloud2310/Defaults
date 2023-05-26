package com.defaults.marketplace.apigateway.models.services;


public class SocialMedia {

    private Integer id;

    private SocialMediaType type;

    private String url;

    private Integer providerId;

    public SocialMedia() {
    }

    public SocialMedia(Integer id, SocialMediaType type, String url, Integer providerId) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.providerId = providerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SocialMediaType getType() {
        return type;
    }

    public void setType(SocialMediaType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}

