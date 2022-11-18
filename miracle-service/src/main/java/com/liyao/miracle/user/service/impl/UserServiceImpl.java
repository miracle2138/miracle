package com.liyao.miracle.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyao.miracle.user.dao.UserFullInfo;
import com.liyao.miracle.user.dao.UserMapper;
import com.liyao.miracle.user.entity.User;
import com.liyao.miracle.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserFullInfo queryUserById(long userId) {
        return userMapper.queryFullUserInfoById(userId);
    }
}
