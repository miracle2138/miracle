package com.liyao.miracle.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liyao.miracle.user.dao.UserFullInfo;
import com.liyao.miracle.user.entity.User;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
public interface IUserService extends IService<User> {

    UserFullInfo queryUserById(long userId);
}
