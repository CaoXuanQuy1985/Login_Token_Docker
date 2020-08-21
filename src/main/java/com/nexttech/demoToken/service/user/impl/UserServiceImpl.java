package com.nexttech.demoToken.service.user.impl;

import com.nexttech.demoToken.model.UserModel;
import com.nexttech.demoToken.repository.UserRepository;
import com.nexttech.demoToken.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    public UserModel findByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    public UserModel findUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public UserModel saveUser(UserModel userModel) {
        return this.userRepository.save(userModel);
    }
}
