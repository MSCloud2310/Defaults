package com.defaults.marketplace.msorders.advisors;

public class ErrorMessage {
    private Integer status;
    private String error;

    public ErrorMessage(Integer status, String error) {
        this.status = status;
        this.error = error;
    }

    public ErrorMessage() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return error;
    }

    public void setMessage(String error) {
        this.error = error;
    }
}
