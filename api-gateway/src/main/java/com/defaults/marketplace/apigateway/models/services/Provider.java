package com.defaults.marketplace.apigateway.models.services;

import java.util.List;

public class Provider {

    private Integer id;

    private Integer userId;

    private String publicName;

    private String pictureUrl;

    private String description;

    private String phoneNumber;

    private String webPage;

    private List<SocialMedia> socialMedia;

    private List<ServiceC> services;

    public Provider() {
    }

    public Provider(Integer id, Integer userId, String publicName, String pictureUrl, String description, String phoneNumber, String webPage, List<SocialMedia> socialMedia, List<ServiceC> services) {
        this.id = id;
        this.userId = userId;
        this.publicName = publicName;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.webPage = webPage;
        this.socialMedia = socialMedia;
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public List<SocialMedia> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<ServiceC> getServices() {
        return services;
    }

    public void setServices(List<ServiceC> services) {
        this.services = services;
    }
}
