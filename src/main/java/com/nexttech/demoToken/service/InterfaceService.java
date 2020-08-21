package com.nexttech.demoToken.service;

import com.nexttech.demoToken.model.UserModel;

public interface InterfaceService<T>{
    Iterable<T> findAll();
    T findByUsernameAndPassword(String username, String password);
    T findUserById(Long id);
    T saveUser(UserModel userModel);
}
