package com.github.nicolasperuch.api.dto;

public class VoteResponse {
    private String message;

    public VoteResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
