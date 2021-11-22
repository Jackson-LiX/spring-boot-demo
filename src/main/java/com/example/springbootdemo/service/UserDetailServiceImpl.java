package com.example.springbootdemo.service;

import com.example.springbootdemo.mapper.UserModelMapper;
import com.example.springbootdemo.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author Jackson
 */
public class UserDetailServiceImpl implements UserDetailService {

    private final UserModelMapper userModelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserDetailServiceImpl(UserModelMapper userModelMapper,
                                 PasswordEncoder passwordEncoder) {
        this.userModelMapper = userModelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userModelMapper.loadUserByUserName(username);
        return new User(userModel.getUserName(), passwordEncoder.encode(userModel.getPassword()), Collections.emptyList());
    }
}
