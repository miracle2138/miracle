package com.liyao.miracleserviceprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 栗垚
 * @Date 2022/11/23
 */
@RestController
public class ProviderController {

    @RequestMapping("/service/provider")
    public String service() {
        return "call service succeed";
    }
}
