package com.example.springbootdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * The class extends UserDetailsService to customize the loadUserByUsername() method for getting token
 * @author jackl
 */
public interface UserDetailService extends UserDetailsService {

}
