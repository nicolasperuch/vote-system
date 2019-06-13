package com.github.nicolasperuch.model;

public class VoteModel {
    private Long rulingId;
    private Long userId;
    private String userCpf;
    private boolean inFavor;

    public Long getRulingId() {
        return rulingId;
    }

    public VoteModel setRulingId(Long rulingId) {
        this.rulingId = rulingId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public VoteModel setUserId(Long userId) {
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
