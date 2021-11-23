package com.example.springbootdemo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootdemo.model.UserModel;

/**
 * The service for user resource management.
 * Implement the IService to use mybatis-plus function.
 *
 * @author Jackson
 */
public interface UserService extends IService<UserModel> {

    /**
     * Get all user
     *
     * @return List<UserModel>
     */
    List<UserModel> getAllUser();

    /**
     * Persist the user
     *
     * @param userModel
     * @return UserModel
     */
    UserModel persistUser(UserModel userModel);

    /**
     * Add user info to redis
     *
     * @param userModel
     */
    void addUserToRedis(UserModel userModel);

    /**
     * Get user from redis by user name
     *
     * @param userName
     * @return UserModel
     */
    UserModel getUserFromRedisByUserName(String userName);

    /**
     * Remove user from redis by user name
     *
     * @param userName
     */
    void removeUserFromRedisByUserName(String userName);
}
