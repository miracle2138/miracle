package com.liyao.miracle.department.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyao.miracle.department.entity.Department;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
public interface IDepartmentService extends IService<Department> {
    IPage<Department> pageQueryDepartments(long pageSize, long pageNum);
}
