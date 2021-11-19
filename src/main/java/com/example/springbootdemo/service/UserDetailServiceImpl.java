package com.example.springbootdemo.service;

import com.example.springbootdemo.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class UserDetailServiceImpl implements UserDetailService{

    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = new UserModel();
        userModel.setUserName(username);
        userModel.setPassword(passwordEncoder.encode("123456"));
        return new User(userModel.getUserName(), userModel.getPassword(), Collections.emptyList());
    }
}
