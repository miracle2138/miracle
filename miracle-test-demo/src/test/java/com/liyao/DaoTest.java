package com.liyao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.liyao.dao.UserDao;
import com.liyao.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@Slf4j
@SpringBootTest
public class DaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Sql(scripts = "/sql/create_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT INTO `t_user`(`id`, `name`) VALUES (1, 'username:1');", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/sql/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void test() {
        // 查询用户
        User user = userDao.getUserById(1L);
        // 校验结果
        assertEquals(1L, user.getId().longValue());
    }
}
