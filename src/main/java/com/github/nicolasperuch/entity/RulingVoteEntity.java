package com.github.nicolasperuch.entity;


import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "ruling_votes", schema = "vote")
public class RulingVoteEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Integer id;
    @Column(name = "ruling_id")
    private Integer rulingId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "in_favor")
    private boolean inFavor;

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

    public Integer isUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isInFavor() {
        return inFavor;
    }

    public void setInFavor(boolean inFavor) {
        this.inFavor = inFavor;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RulingVoteEntity{");
        sb.append("id=").append(id);
        sb.append(", rulingId=").append(rulingId);
        sb.append(", userId=").append(userId);
        sb.append(", inFavor=").append(inFavor);
        sb.append('}');
        return sb.toString();
    }
}
