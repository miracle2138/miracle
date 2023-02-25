package com.liyao.miracle.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.data.HyperlinkData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.liyao.miracle.excel.model.DemoData1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author 栗垚
 * @Date 2022/11/29
 */
@RestController
public class ExcelDemoController {

    @RequestMapping("/excel/download")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easy excel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DemoData1.class).sheet("模板").doWrite(demoData1());
    }

    private List<DemoData1> demoData1() {
        List<DemoData1> demoDataList = new ArrayList<>();
        DemoData1 d1 = new DemoData1();
        d1.setString("ly");
        d1.setDoubleData(0.1D);
        d1.setIgnore("ignore");
        d1.setDate(new Date());

//        HyperlinkData data = new HyperlinkData();
//        data.setAddress("www.baidu.com");
//        data.setHyperlinkType(HyperlinkData.HyperlinkType.URL);
//
//        WriteCellData<String> link = new WriteCellData<>("这特么是一个超链接哦");
//        link.setHyperlinkData(data);
//        d1.setLink(link);
        // 设置超链接
        WriteCellData<String> hyperlink = new WriteCellData<>("官方网站");
        HyperlinkData hyperlinkData = new HyperlinkData();
        hyperlink.setHyperlinkData(hyperlinkData);
        hyperlinkData.setAddress("https://github.com/alibaba/easyexcel");
        hyperlinkData.setHyperlinkType(HyperlinkData.HyperlinkType.URL);
        d1.setLink(hyperlink);

        DemoData1 d2 = new DemoData1();
        d2.setString("lzy");
        d2.setDoubleData(0.2D);
        d2.setDate(new Date());

        demoDataList.add(d1);
        demoDataList.add(d2);
        return demoDataList;
    }
}
