package com.example.springbootdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Jackson
 */
@Data
@TableName("user_access.user_access")
public class UserModel {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String password;
}
