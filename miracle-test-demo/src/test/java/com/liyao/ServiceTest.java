package com.liyao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.liyao.dao.UserDao;
import com.liyao.dto.User;
import com.liyao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@Slf4j
@SpringBootTest
public class ServiceTest {

    @MockBean
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        Mockito.when(userDao.getUserById(1L)).thenReturn(
                User.builder().id(1L).name("n1").build());
        Mockito.when(userDao.getUserById(2L)).thenReturn(
                User.builder().id(2L).name("n2").build());
        User user1 = userService.getUserById(1L);
        User user2 = userService.getUserById(2L);
        assertEquals("n1", user1.getName());
        assertEquals("n2", user2.getName());
    }
}
