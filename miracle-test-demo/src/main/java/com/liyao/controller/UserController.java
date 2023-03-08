package com.liyao.controller;

import com.liyao.dto.User;
import com.liyao.service.UserService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/api/user")
    public User hello(@RequestParam Long id) {
        return userService.getUserById(id);
    }
}
