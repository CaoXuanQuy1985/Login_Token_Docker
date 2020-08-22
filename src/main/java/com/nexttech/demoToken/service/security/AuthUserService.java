package com.nexttech.demoToken.service.security;

import com.nexttech.demoToken.model.UserModel;
import com.nexttech.demoToken.service.InterfaceService;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface AuthUserService extends InterfaceService<UserModel> {

    UserModel findByToken(String token);

    ResponseEntity<String> login(HttpServletRequest request, String username, String password);

    String refreshToken(HttpServletRequest request);

    ResponseEntity<String> getResource(HttpServletRequest request);
}
