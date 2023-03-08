package com.liyao.service;

import com.liyao.dao.UserDao;
import com.liyao.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserById(long id) {
        return userDao.getUserById(id);
    }
}
