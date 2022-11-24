package com.liyao.miracleserviceconsumer.controller;

import com.liyao.miracleserviceconsumer.thirdcall.ServiceProviderCaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/24
 */
@Slf4j
@RestController
public class ConsumerFeignController {

    @Autowired
    private ServiceProviderCaller serviceProviderCaller;

    @RequestMapping("/service/consumer/feign")
    public String f() {
        String ret = serviceProviderCaller.callServiceProvider();
        log.info("ret: {}", ret);
        return ret;
    }
}
