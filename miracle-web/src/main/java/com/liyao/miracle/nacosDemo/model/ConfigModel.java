package com.liyao.miracle.nacosDemo.model;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @Author 栗垚
 * @Date 2022/11/21
 */
@Data
@Component
@NacosConfigurationProperties(dataId = "liyao.test.json", groupId = "app1", autoRefreshed = true)
public class ConfigModel {

    private String stringKey;

    private int intKey;

    @Override
    public String toString() {
        return "ConfigModel{" +
                "stringKey='" + stringKey + '\'' +
                ", intKey='" + intKey + '\'' +
                '}';
    }
}
