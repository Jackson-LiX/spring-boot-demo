package com.example.springbootdemo.config;

import com.example.springbootdemo.mapper.UserModelMapper;
import com.example.springbootdemo.service.UserDetailServiceImpl;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfig {

    @Bean
    UserService userService(UserModelMapper userModelMapper,
                            RedisTemplate<String, Object> commonRedisTemplate) {
        return new UserServiceImpl(userModelMapper, commonRedisTemplate);
    }



    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new UserDetailServiceImpl(passwordEncoder);
    }

}
