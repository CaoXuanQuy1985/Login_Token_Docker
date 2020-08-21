package com.nexttech.demoToken.controller.information;

import com.nexttech.demoToken.model.UserModel;
import com.nexttech.demoToken.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    public Iterable<UserModel> all() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/user/testing")
    public UserModel getUserByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    @PostMapping("/user/save")
    public UserModel save(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }
}
