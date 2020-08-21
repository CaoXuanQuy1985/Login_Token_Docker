package com.nexttech.demoToken.service.security.impl;

import com.nexttech.demoToken.model.UserModel;
import com.nexttech.demoToken.repository.UserRepository;
import com.nexttech.demoToken.service.security.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private UserRepository userRepository;

    public AuthUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> login(HttpServletRequest request, String username, String password) {
        UserModel user =  this.userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            String token = request.getHeader("bearer");
            if (token == null) {
                token = UUID.randomUUID().toString();
                user.setToken(token);
                userRepository.save(user);

                return ResponseEntity.ok(token);
            } else {
                UserModel userDB = this.findByToken(token);
                String tokenOnDB = userDB.getToken();
                String currentToken = user.getToken();
                if (currentToken.equals(tokenOnDB)) {
                    return ResponseEntity.ok("Login Successfully !!!");
                } else {
                    return ResponseEntity.badRequest().body("The Token is not match !!!, try another token !!!");
                }
            }
        }

        return ResponseEntity.badRequest().body("This request is bad, try again with another account !!!");
    }

    public Iterable<UserModel> findAll() {
        return null;
    }
    public UserModel findByUsernameAndPassword(String username, String password) {return null;}
    public UserModel findUserById(Long id) {return null;}

    public UserModel findByToken(String token) {
        return this.userRepository.findByToken(token);
    }

    public UserModel saveUser(UserModel userModel) {return null;}
}
