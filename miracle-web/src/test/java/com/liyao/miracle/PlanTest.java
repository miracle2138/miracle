package com.liyao.miracle;

import com.liyao.miracle.plan.dao.PlanDao;
import com.liyao.miracle.plan.entity.Plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/12/26
 */
@Slf4j
@SpringBootTest(classes = PlanTest.class)
public class PlanTest {

    @Autowired
    private PlanDao planDao;

    @Test
    public void test() {
        Plan plan = planDao.querySum();
        log.info("{}", plan);
    }
}
