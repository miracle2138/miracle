package com.liyao.miracle.vue.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/12/12
 */
@Slf4j
@CrossOrigin
@RestController
public class VueDemoController {

    @RequestMapping("/post")
    public Map<String, Object> get(@RequestParam("postId") Long postId) throws InterruptedException {
        log.info("postId: {}", postId);
        Map<String, Object> data = new HashMap<>();
        data.put("title", "This is title");
        data.put("body", "This is body");
        return data;
    }
}
