package com.liyao.miracle;

import com.liyao.miracle.validationDemo.model.QueryParamDemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@SpringBootTest
@Slf4j
public class LombokTest {
    @Test
    public void test() {
        QueryParamDemo demo = QueryParamDemo.builder()
                .id(1L)
                .value("val")
                .name("n1")
                .name("n2")
                .build();
        log.info("demo: {}", demo);
        log.info("lombok 还是香啊");
    }
}