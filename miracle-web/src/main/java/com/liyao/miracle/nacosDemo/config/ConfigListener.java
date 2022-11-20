package com.liyao.miracle.nacosDemo.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 栗垚
 * @Date 2022/11/20
 */
@Component
public class ConfigListener {

    private static final Logger logger = LoggerFactory.getLogger(ConfigListener.class);

    /**
     * 基于注解监听配置
     *
     * @param newContent
     * @throws Exception
     */
    @NacosConfigListener(dataId = "liyao.test.string", timeout = 500)
    public void onChange(String newContent) {
        logger.info("配置变更为 : {}" + newContent);
    }
}