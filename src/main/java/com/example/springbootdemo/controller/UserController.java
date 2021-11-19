package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.springbootdemo.model.UserModel;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public List<UserModel> getAllUser() {
        return userService.list();
    }

    @GetMapping("/redis/{userName}")
    public UserModel getUserFromRedisByUserName(@PathVariable String userName) {
        return userService.getUserFromRedisByUserName(userName);
    }

    @PostMapping("/redis")
    public void addUserInfoToRedis(@RequestBody UserModel userModel) {
        userService.addUserToRedis(userModel);
    }

    @DeleteMapping("/redis/{userName}")
    public void deleteUserFromRedisByUserName(@PathVariable String userName) {
        userService.removeUserFromRedisByUserName(userName);
    }
}
