package com.liyao.miraclecloudweb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/24
 */
@Slf4j
@RestController
@RefreshScope
public class CwController {

    @Value("${key1}")
    private String value;

    @Value("${kk}")
    private String kk;

    @RequestMapping("/c/h")
    public String f() {
        log.info("value: {}, kk: {}", value, kk);
        return "";
    }

}
