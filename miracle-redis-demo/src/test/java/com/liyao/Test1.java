package com.liyao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Unit test for simple App.
 */
@Slf4j
@SpringBootTest
public class Test1 {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testStringSetKey() {
        redisTemplate.opsForValue().set("kkk", "vvv");
    }
}
