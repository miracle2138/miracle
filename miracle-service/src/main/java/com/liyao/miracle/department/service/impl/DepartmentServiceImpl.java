package com.liyao.miracle.department.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyao.miracle.department.dao.DepartmentMapper;
import com.liyao.miracle.department.entity.Department;
import com.liyao.miracle.department.service.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public IPage<Department> pageQueryDepartments(long pageSize, long pageNum) {
        IPage<Department> page = new Page<>(pageNum, pageSize);
        return departmentMapper.selectPage(page, null);
    }
}
