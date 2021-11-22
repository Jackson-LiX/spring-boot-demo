package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.model.UserModel;
import org.apache.ibatis.annotations.Param;

/**
 * The mapper for user resource management.
 * Implement the BaseMapper to use mybatis-plus function.
 *
 * @author Jackson
 */
public interface UserModelMapper extends BaseMapper<UserModel> {

    /**
     * Load user by user name
     * @param userName
     * @return
     */
    UserModel loadUserByUserName(@Param("userName") String userName);
}
