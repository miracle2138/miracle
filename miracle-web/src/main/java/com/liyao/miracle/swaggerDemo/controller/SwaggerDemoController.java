package com.liyao.miracle.swaggerDemo.controller;

import com.liyao.miracle.swaggerDemo.model.SwaggerDemoParams;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author 栗垚
 * @Date 2022/11/29
 */
@Api(value = "SwaggerDemo API", tags = {"demo"})
@RestController
public class SwaggerDemoController {

    @ApiOperation(value = "更新接口", notes = "更新用户信息")
    @PostMapping("/swagger/demo")
    public String update(@RequestBody SwaggerDemoParams params) {
        return "";
    }
}
