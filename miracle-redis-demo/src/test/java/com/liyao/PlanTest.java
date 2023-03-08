package com.liyao;

import com.liyao.miracle.plan.dao.PlanDao;
import com.liyao.miracle.plan.entity.Plan;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 栗垚
 * @Date 2023/3/1
 */
@Slf4j
@SpringBootTest
public class PlanTest {

    @Autowired
    private PlanDao planDao;

    @Test
    public void test() {
        Plan plan = planDao.querySum();
        log.info("{}", plan);
    }
}
