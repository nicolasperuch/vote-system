package com.github.nicolasperuch.model;

public class OpenRulingForVoteModel {
    private Long rulingId;
    private String remainingTime;

    public Long getRulingId() {
        return rulingId;
    }

    public void setRulingId(Long rulingId) {
        this.rulingId = rulingId;
    }

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OpenRulingForVoteModel{");
        sb.append("rulingId=").append(rulingId);
        sb.append(", remainingTime='").append(remainingTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
