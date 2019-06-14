package com.github.nicolasperuch.model;

public class VoteModel {
    private Integer rulingId;
    private Integer userId;
    private String userCpf;
    private boolean inFavor;

    public Integer getRulingId() {
        return rulingId;
    }

    public VoteModel setRulingId(Integer rulingId) {
        this.rulingId = rulingId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public VoteModel setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public VoteModel setUserCpf(String userCpf) {
        this.userCpf = userCpf;
        return this;
    }

    public boolean isInFavor() {
        return inFavor;
    }

    public VoteModel setInFavor(boolean inFavor) {
        this.inFavor = inFavor;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("VoteModel{");
        sb.append("rulingId=").append(rulingId);
        sb.append(", userId=").append(userId);
        sb.append(", userCpf='").append(userCpf).append('\'');
        sb.append(", inFavor=").append(inFavor);
        sb.append('}');
        return sb.toString();
    }
}
