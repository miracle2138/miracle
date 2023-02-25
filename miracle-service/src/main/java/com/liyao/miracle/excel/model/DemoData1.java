package com.liyao.miracle.excel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.liyao.miracle.excel.converter.BuildTypeConverter;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 栗垚
 * @Date 2022/11/28
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(30)
public class DemoData1 {
    @ExcelProperty({"主标题", "字符串标题"})
    private String string;
    @ExcelProperty({"主标题", "日期标题"})
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty("地址")
    private WriteCellData<String> link;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;

    @ExcelProperty(value = "项目类型", converter = BuildTypeConverter.class)
    private Integer buildType;
}
