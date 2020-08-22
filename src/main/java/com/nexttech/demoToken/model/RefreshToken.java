package com.nexttech.demoToken.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    private String valueRefreshToken;

    private Long userId;

    @OneToOne
    private UserModel userModel;

    public RefreshToken() {}

    public RefreshToken(String valueRefreshToken, Long userId) {
        this.valueRefreshToken = valueRefreshToken;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValueRefreshToken() {
        return valueRefreshToken;
    }

    public void setValueRefreshToken(String valueRefreshToken) {
        this.valueRefreshToken = valueRefreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
