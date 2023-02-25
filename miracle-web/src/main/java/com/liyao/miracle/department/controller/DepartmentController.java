package com.liyao.miracle.department.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liyao.miracle.department.entity.Department;
import com.liyao.miracle.department.service.IDepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/page")
    public String pageQuery(@RequestParam long pageNum, @RequestParam long pageSize) {
        IPage<Department> page = departmentService.pageQueryDepartmentsByParentId(pageSize, pageNum, 1L);
        logger.info("page: {}", page);
        return "";
    }

    @RequestMapping("/add")
    public String add() {
        Department root = new Department();
        root.setId(1);
        root.setDepartmentName("公司总部");
        root.setTree("0");

        Department d1 = new Department();
        d1.setId(2);
        d1.setDepartmentName("研发部门一");
        d1.setTree("0.1");
        d1.setParentId(1);

        Department d2 = new Department();
        d2.setId(3);
        d2.setDepartmentName("研发部门二");
        d2.setTree("0.1");
        d2.setParentId(1);

        Department d3 = new Department();
        d3.setId(4);
        d3.setDepartmentName("研发部门三");
        d3.setTree("0.1");
        d3.setParentId(1);

        Department d4 = new Department();
        d4.setId(5);
        d4.setDepartmentName("研发部门三 产品部");
        d4.setTree("0.1.4");
        d4.setParentId(4);

        List<Department> list = new ArrayList<>();
        list.add(root);
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);

        departmentService.saveBatch(list);
        return "";
    }
}
