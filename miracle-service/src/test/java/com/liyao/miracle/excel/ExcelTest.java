package com.liyao.miracle.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.data.HyperlinkData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.liyao.miracle.excel.interceptor.CustomCellWriteHandler;
import com.liyao.miracle.excel.model.DemoData;
import com.liyao.miracle.excel.model.DemoData1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/28
 */
@Slf4j
@SpringBootTest(classes = ExcelTest.class)
public class ExcelTest {

    public List<DemoData> demoData() {
        List<DemoData> demoDataList = new ArrayList<>();
        DemoData d1 = new DemoData();
        d1.setString("ly");
        d1.setDoubleData(0.1D);
        d1.setIgnore("ignore");
        d1.setDate(new Date());

        DemoData d2 = new DemoData();
        d2.setString("lzy");
        d2.setDoubleData(0.2D);
        d2.setDate(new Date());

        demoDataList.add(d1);
        demoDataList.add(d2);
        return demoDataList;
    }

    public List<DemoData1> demoData1() {
        List<DemoData1> demoDataList = new ArrayList<>();
        DemoData1 d1 = new DemoData1();
        d1.setString("ly");
        d1.setDoubleData(0.1D);
        d1.setIgnore("ignore");
        d1.setDate(new Date());
        d1.setBuildType(20007);

        HyperlinkData data = new HyperlinkData();
        data.setAddress("https://www.baidu.com");
        data.setHyperlinkType(HyperlinkData.HyperlinkType.URL);

        WriteCellData<String> link = new WriteCellData<>("这特么是一个超链接哦");
        link.setHyperlinkData(data);
        d1.setLink(link);

        DemoData1 d2 = new DemoData1();
        d2.setString("lzy");
        d2.setDoubleData(0.2D);
        d2.setDate(new Date());

        demoDataList.add(d1);
        demoDataList.add(d2);
        return demoDataList;
    }

    @Test
    public void test() {
        EasyExcel.write(System.currentTimeMillis() + "a.xlsx", DemoData.class)
                .sheet("模板")
                .excludeColumnFieldNames(Collections.singletonList("string1"))
                .doWrite(demoData());
    }

    @Test
    public void test1() {
        EasyExcel.write(System.currentTimeMillis() + "a.xlsx", DemoData1.class)
                .sheet("模板")
                .registerWriteHandler(new CustomCellWriteHandler())
                .doWrite(demoData1());
    }
}
