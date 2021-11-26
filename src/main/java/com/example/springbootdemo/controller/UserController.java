package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.UserModel;
import com.example.springbootdemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller for user resource management
 *
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
     * Add user
     *
     * @param userModel
     * @return
     */
    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.persistUser(userModel));
    }

    /**
     * Update user
     *
     * @param userModel
     * @return UserModel
     */
    @PutMapping
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel) {
        return ResponseEntity.ok(userService.persistUser(userModel));
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
