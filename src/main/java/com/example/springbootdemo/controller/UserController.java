package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springbootdemo.model.UserModel;

import java.util.List;

/**
 * @author Jackson
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users from DB
     *
     * @return List<UserModel>
     */
    @GetMapping("/allUser")
    public ResponseEntity<List<UserModel>> getAllUser() {
        return ResponseEntity.ok(userService.list());
    }

    /**
     * Get user from redis by user name
     *
     * @param userName
     * @return UserModel
     */
    @GetMapping("/redis/{userName}")
    public ResponseEntity<UserModel> getUserFromRedisByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getUserFromRedisByUserName(userName));
    }

    /**
     * Add user to redis
     *
     * @param userModel
     */
    @PostMapping("/redis")
    public void addUserInfoToRedis(@RequestBody UserModel userModel) {
        userService.addUserToRedis(userModel);
    }

    /**
     * Delete user from redis by user name
     *
     * @param userName
     */
    @DeleteMapping("/redis/{userName}")
    public void deleteUserFromRedisByUserName(@PathVariable String userName) {
        userService.removeUserFromRedisByUserName(userName);
    }
}
