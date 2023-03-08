package com.liyao.model;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @Author 栗垚
 * @Date 2023/3/7
 */
@Data
public class ExcelDataVo {

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("姓名")
    private String name;

}
