package com.liyao.miracle.nacosDemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import com.liyao.miracle.nacosDemo.config.ConfigGetByServiceDemo;
import com.liyao.miracle.nacosDemo.model.ConfigModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 栗垚
 * @Date 2022/11/20
 */
@RestController
@NacosPropertySources({
        @NacosPropertySource(dataId = "liyao.test.string2", groupId = "app1", autoRefreshed = true),
})
public class NacosDemoController {

    private static final Logger logger = LoggerFactory.getLogger(NacosDemoController.class);

    @NacosValue(value = "${key1:key1D}", autoRefreshed = true)
    private String value;

    @NacosValue(value = "${key2:key2D}", autoRefreshed = true)
    private String value1;

    @NacosValue(value = "${key3:key3D}", autoRefreshed = true)
    private String value2;

    @Autowired
    private ConfigModel configModel;

    @Autowired
    private ConfigGetByServiceDemo configGetByServiceDemo;

    @RequestMapping("/n/h")
    public String h() {
        logger.info("value: {}", value);
        logger.info("value1: {}", value1);
        logger.info("value2: {}", value2);

        logger.info("config model: {}", configModel);
        return "";
    }

    @RequestMapping("/n/h1")
    public String h1() throws NacosException {
        configGetByServiceDemo.test();
        return "";
    }
}
