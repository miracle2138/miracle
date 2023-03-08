package com.liyao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/3/2
 */
@Slf4j
@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        Map<String, Object> val = new HashMap<>();
        redisTemplate.opsForValue().set("a", val);
    }

    @Test
    public void testPipe() {
        List<Object> ret = stringRedisTemplate.executePipelined((RedisCallback<String>) connection -> {
            for (int i = 0; i < 3; i++) {
                byte[] key = ("k" + i).getBytes(StandardCharsets.UTF_8);
                byte[] val = ("v" + i).getBytes(StandardCharsets.UTF_8);
                connection.set(key, val);
            }

            for (int i = 0; i < 3; i++) {
                byte[] key = ("k" + i).getBytes(StandardCharsets.UTF_8);
                connection.get(key);
            }
            return null;
        });
        log.info("ret: {}", ret);
    }

    @Test
    public void testSessionPipe() {
        List<Object> ret = stringRedisTemplate.executePipelined(new SessionCallback<String>() {
            @Override
            public <K, V> String execute(@Nonnull RedisOperations<K, V> operations) throws DataAccessException {
                for (int i = 0; i < 3; i++) {
                    operations.opsForValue().get("k" + i);
                }
                return null;
            }
        });
        log.info("ret: {}", ret);
    }
}
