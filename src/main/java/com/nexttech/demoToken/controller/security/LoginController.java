package com.nexttech.demoToken.controller.security;

import com.nexttech.demoToken.service.security.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1")
public class LoginController {

    private AuthUserService authUserService;

    @Autowired
    public LoginController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        return this.authUserService.login(request, username, password);
    }
}
