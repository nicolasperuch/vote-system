package com.github.nicolasperuch.client.dto;

public class CpfValidationResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CpfValidationResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
