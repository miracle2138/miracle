package com.liyao.miracle.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyao.miracle.user.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Nullable
    UserFullInfo queryFullUserInfoById(@Param("userId") long userId);
    
}