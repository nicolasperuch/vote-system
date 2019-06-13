package com.github.nicolasperuch.api.dto;

public class VoteDto {
    private Long userId;
    private String userCpf;
    private boolean inFavor;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public boolean isInFavor() {
        return inFavor;
    }

    public void setInFavor(boolean inFavor) {
        this.inFavor = inFavor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VoteDto{");
        sb.append("userId=").append(userId);
        sb.append(", userCpf='").append(userCpf).append('\'');
        sb.append(", inFavor=").append(inFavor);
        sb.append('}');
        return sb.toString();
    }
}
