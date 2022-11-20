package com.liyao.miracle.user;

import com.liyao.miracle.user.dao.UserFullInfo;
import com.liyao.miracle.user.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Value("")
    private String value;

    @RequestMapping("/all")
    public String allUsers(@RequestParam long userId) {
        UserFullInfo user = userService.queryUserById(userId);
        
        return "";
    }
}
