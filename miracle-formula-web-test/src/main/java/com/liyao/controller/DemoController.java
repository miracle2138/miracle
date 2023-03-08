package com.liyao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.liyao.Model;
import com.spicdt.formula.framework.common.pojo.CommonResult;
import com.spicdt.formula.framework.xss.core.clean.XssCleaner;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import static com.liyao.enums.MyErrorCode.ProjectNotExist;
import static com.spicdt.formula.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static com.spicdt.formula.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * @Author 栗垚
 * @Date 2023/3/6
 */
@Slf4j
@RestController
public class DemoController {

    @Resource(name = "xssJacksonCustomizer")
    private Jackson2ObjectMapperBuilderCustomizer xssJacksonCustomizer;

    @Resource
    private XssCleaner xssCleaner;

    @RequestMapping("/hello")
    public CommonResult<Map<String, Object>> hello(@RequestParam("name") String name) {
        if ("error".equals(name)) {
            throw exception(BAD_REQUEST);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("k", 1);
        return CommonResult.success(map);
    }

    @RequestMapping("/hello1")
    public CommonResult<Map<String, Object>> hello1(@RequestBody Model m) throws JsonProcessingException {
        String s = xssCleaner.clean(m.getValue());
        log.info("{}, {}", m, s);
        Map<String, Object> map = new HashMap<>();
        map.put("k1", Long.MAX_VALUE);
        map.put("k2", 1L);
        return CommonResult.success(map);
    }

    @RequestMapping("/hello2")
    public CommonResult<Object> hello2() {
        return CommonResult.success(LocalDateTime.now());
    }

    @RequestMapping("/projects")
    public CommonResult<Object> get(@RequestParam String projectName) {
        if ("不配存在的项目".equals(projectName)) {
            throw exception(ProjectNotExist, projectName);
        }
        return CommonResult.success(new Object());
    }
}
