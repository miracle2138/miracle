package com.liyao.demo;

import com.liyao.miracle.Bootstrap;
import com.liyao.miracle.plan.dao.PlanDao;
import com.liyao.miracle.plan.entity.Plan;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author 栗垚
 * @Date 2022/12/26
 */
@Slf4j
@SpringBootTest(classes = Bootstrap.class)
public class PlanTest {

    @Autowired
    private PlanDao planDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        Plan plan = planDao.querySum();
        log.info("{}", plan);

        stringRedisTemplate.opsForValue().set("k1", "v1");
    }
}
