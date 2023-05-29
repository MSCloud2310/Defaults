package com.defaults.marketplace.apigateway.models.users;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {
    @JsonProperty
    private String message;
    @JsonProperty
    private String token;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

    @JsonProperty
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }

    @JsonProperty
    public void setToken(String token) {
        this.token = token;
    }
}
