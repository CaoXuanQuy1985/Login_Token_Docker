package com.nexttech.demoToken.service.security.impl;

import com.nexttech.demoToken.model.ExpiredToken;
import com.nexttech.demoToken.model.RefreshToken;
import com.nexttech.demoToken.model.UserModel;
import com.nexttech.demoToken.repository.ExpiredRepository;
import com.nexttech.demoToken.repository.RefreshRepository;
import com.nexttech.demoToken.repository.UserRepository;
import com.nexttech.demoToken.service.security.AuthUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private UserRepository userRepository;

    private RefreshRepository refreshRepository;

    private ExpiredRepository expiredRepository;

    public AuthUserServiceImpl(UserRepository userRepository, RefreshRepository refreshRepository, ExpiredRepository expiredRepository) {
        this.userRepository = userRepository;
        this.refreshRepository = refreshRepository;
        this.expiredRepository = expiredRepository;
    }

    public ResponseEntity<String> login(HttpServletRequest request, String username, String password) {

        UserModel user = this.userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            String token = request.getHeader("bearer");
            if (token == null) {
                token = UUID.randomUUID().toString();
                user.setToken(token);
                userRepository.save(user);

                return ResponseEntity.ok(token);
            } else {
                Timestamp expiredDateTimeToken = user.getExpiredDateToken();
                Timestamp nowTime = new Timestamp(System.currentTimeMillis());
                if (nowTime.compareTo(expiredDateTimeToken) > 0) {
                    token = refreshToken(request);
                }
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

    public ResponseEntity<String> getResource(HttpServletRequest request) {
        String token = request.getHeader("bearer");

        if (token == null) {
            return ResponseEntity.badRequest().body("You have to login to access this Resource !!!");
        } else {
            UserModel user = this.findByToken(token);
            Timestamp expiredDateTimeToken = user.getExpiredDateToken();
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());

            if (nowTime.compareTo(expiredDateTimeToken) > 0) {
                System.out.println("Token has expired, calling refreshToken() function or Login again to get new Token then try login again");
                return ResponseEntity.badRequest().body("Token has expired, please calling method refreshToken or login again to create new Token !!!");
            }
        }

        return ResponseEntity.ok("This is important Resource");
    }

    public String refreshToken(HttpServletRequest request) {
        String tokenRequest = request.getHeader("bearer");
        UserModel userRequest = this.findByToken(tokenRequest);
        Long userRequestId = userRequest.getId();

        String newToken = UUID.randomUUID().toString();

        RefreshToken refreshToken = new RefreshToken(newToken, userRequestId);
        refreshRepository.save(refreshToken);
        userRequest.setToken(newToken);
        Timestamp newCreatedDateToken = new Timestamp(System.currentTimeMillis());
        Timestamp newExpiredDateToken = new Timestamp(System.currentTimeMillis() + 60000);
        userRequest.setCreatedDateToken(newCreatedDateToken);
        userRequest.setExpiredDateToken(newExpiredDateToken);

        userRequest.setToken(newToken);
        userRepository.save(userRequest);

        ExpiredToken expiredToken = new ExpiredToken(tokenRequest);
        expiredRepository.save(expiredToken);

        return newToken;
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
