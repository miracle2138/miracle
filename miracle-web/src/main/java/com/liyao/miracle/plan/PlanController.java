package com.liyao.miracle.plan;

import com.liyao.miracle.plan.dao.PlanDao;
import com.liyao.miracle.plan.entity.Plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @Author 栗垚
 * @Date 2022/12/26
 */
@RestController
public class PlanController {
    @Autowired
    private PlanDao planDao;

    @RequestMapping("/plan1")
    public String plan(
            @RequestParam("a") int a,
            @RequestParam("b") int b) {
        Plan plan = planDao.querySum();
        plan.setAmount(plan.getAmount().stripTrailingZeros());
        BigDecimal d = new BigDecimal("0");
        BigDecimal d1 = new BigDecimal("0.000000");
        BigDecimal d2 = new BigDecimal("000.00000000");
        return "";
    }
}
