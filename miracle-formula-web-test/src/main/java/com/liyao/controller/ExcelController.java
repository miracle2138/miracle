package com.liyao.controller;

import com.liyao.model.ExcelDataVo;
import com.spicdt.formula.framework.excel.core.util.ExcelUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 栗垚
 * @date 2023/3/7
 */
@Slf4j
@RestController
public class ExcelController {

    @RequestMapping("/export")
    public void exportFile(HttpServletResponse response) throws IOException {
        List<ExcelDataVo> data = getTestData();
        ExcelUtils.write(response, "测试.xlsx", "测试", ExcelDataVo.class, data);
    }

    private List<ExcelDataVo> getTestData() {
        List<ExcelDataVo> data = new ArrayList<>();
        ExcelDataVo v1 = new ExcelDataVo();
        v1.setId(1L);
        v1.setName("ly1");

        ExcelDataVo v2 = new ExcelDataVo();
        v2.setId(2L);
        v2.setName("ly2");

        data.add(v1);
        data.add(v2);
        return data;
    }

    @RequestMapping("/import")
    public void importFile(MultipartFile file) throws IOException {
        List<ExcelDataVo> list = ExcelUtils.read(file, ExcelDataVo.class);
        log.info("list: {}", list);
    }
}
