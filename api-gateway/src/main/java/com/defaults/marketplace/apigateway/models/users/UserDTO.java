package com.defaults.marketplace.apigateway.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String email;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private String birthday;
    @JsonProperty
    private String pictureUrl;
    @JsonProperty
    private String description;
    @JsonProperty
    private UserRole role;

    public UserDTO() {
    }

    @JsonProperty
    public Integer getId() {
        return id;
    }

    @JsonProperty
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty

    public String getEmail() {
        return email;
    }

    @JsonProperty

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty
    public String getPictureUrl() {
        return pictureUrl;
    }

    @JsonProperty
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public UserRole getRole() {
        return role;
    }

    @JsonProperty
    public void setRole(UserRole role) {
        this.role = role;
    }
}
