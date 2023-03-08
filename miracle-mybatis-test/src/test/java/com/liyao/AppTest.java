package com.liyao;


import com.liyao.mapper.UserMapper;
import com.liyao.model.UserDO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO userDO = new UserDO();
        userDO.setUsername("ly");
        userDO.setPassword("ply");
        userDO.setCreateTime(new Date());
        userMapper.insert(userDO);
    }
}
