package com.liyao.miracle.plan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyao.miracle.plan.entity.Plan;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 栗垚
 * @Date 2022/12/26
 */
@Mapper
public interface PlanDao extends BaseMapper<Plan> {
    Plan querySum();
}
