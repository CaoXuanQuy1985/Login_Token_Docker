package com.nexttech.demoToken.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class UserModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String username;

    private String password;

    private String token;

    private Timestamp createdDateToken = new Timestamp(System.currentTimeMillis());

    private Timestamp expiredDateToken = new Timestamp(System.currentTimeMillis() + 60000);

    @OneToOne(mappedBy = "userModel")
    private RefreshToken refreshToken;

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreatedDateToken() {
        return createdDateToken;
    }

    public void setCreatedDateToken(Timestamp createdDateToken) {
        this.createdDateToken = createdDateToken;
    }

    public Timestamp getExpiredDateToken() {
        return expiredDateToken;
    }

    public void setExpiredDateToken(Timestamp expiredDateToken) {
        this.expiredDateToken = expiredDateToken;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }
}
