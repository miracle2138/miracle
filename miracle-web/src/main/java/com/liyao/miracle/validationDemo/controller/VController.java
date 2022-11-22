package com.liyao.miracle.validationDemo.controller;

import com.liyao.miracle.common.ValidateGroup;
import com.liyao.miracle.validationDemo.model.QueryParamDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@Slf4j
@RestController
public class VController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/v/h")
    public String h(@Valid @RequestBody QueryParamDemo param, BindingResult result) {
        String m1 = messageSource.getMessage("k.v.message", null, "default", Locale.getDefault());
        log.info("m1: {}", m1);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(
                    e -> log.info("{}", e.getDefaultMessage())
            );
        }
        log.info("param: {}", param);
        return "";
    }

    @RequestMapping("/v/h1")
    public String h1(@Valid @RequestBody QueryParamDemo param) {
        log.info("param: {}", param);
        return "";
    }

    @RequestMapping("v/h2")
    public String h2(@Validated(ValidateGroup.GroupAdd.class) @RequestBody QueryParamDemo param) {
        return "";
    }

    @RequestMapping("v/h3")
    public String h3(@Validated(ValidateGroup.GroupUpdate.class) @RequestBody QueryParamDemo param) {
        return "";
    }
}
