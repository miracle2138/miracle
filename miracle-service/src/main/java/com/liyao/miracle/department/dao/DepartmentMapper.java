package com.liyao.miracle.department.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liyao.miracle.department.entity.Department;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {
    IPage<Department> pageQueryDepartments(IPage<Department> pageParams, @Param("parentId") Long parentId);
}
