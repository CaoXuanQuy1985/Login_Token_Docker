package com.nexttech.demoToken.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExpiredToken {

    @Id
    @GeneratedValue
    private Long id;

    private String valueExpiredToken;

    public ExpiredToken() {}

    public ExpiredToken(String valueExpiredToken) {
        this.valueExpiredToken = valueExpiredToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueExpiredToken() {
        return valueExpiredToken;
    }

    public void setValueExpiredToken(String valueExpiredToken) {
        this.valueExpiredToken = valueExpiredToken;
    }
}
