package com.example.springbootdemo.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootdemo.model.UserModel;

public interface UserService extends IService<UserModel> {

    /**
     * Get all user
     *
     * @return List<UserModel>
     */
    List<UserModel> getAllUser();

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
