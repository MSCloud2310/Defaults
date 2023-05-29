package com.defaults.marketplace.apigateway.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationRequest {
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationRequest() {
    }

    public String toString() {
        return "AuthenticationRequest(email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }
}
