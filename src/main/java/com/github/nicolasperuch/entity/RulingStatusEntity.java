package com.github.nicolasperuch.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ruling_status", schema = "vote")
public class RulingStatusEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;
    @Column(name = "ruling_id")
    private Integer rulingId;
    @Column(name = "open_for_vote")
    private boolean isOpenForVote;
    @Column(name = "finished")
    private boolean isRulingFinished;
    @Column(name = "result")
    private boolean rulingResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRulingId() {
        return rulingId;
    }

    public void setRulingId(Integer rulingId) {
        this.rulingId = rulingId;
    }

    public boolean isOpenForVote() {
        return isOpenForVote;
    }

    public void setOpenForVote(boolean openForVote) {
        isOpenForVote = openForVote;
    }

    public boolean isRulingFinished() {
        return isRulingFinished;
    }

    public void setRulingFinished(boolean rulingFinished) {
        isRulingFinished = rulingFinished;
    }

    public boolean isRulingResult() {
        return rulingResult;
    }

    public void setRulingResult(boolean rulingResult) {
        this.rulingResult = rulingResult;
    }
}
