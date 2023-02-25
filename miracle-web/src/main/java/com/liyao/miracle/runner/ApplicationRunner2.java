package com.liyao.miracle.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/1/1
 */
@Slf4j
@Component
public class ApplicationRunner2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("runner2 start");
        log.info("args: {}", args);
        log.info("runner2 end");
    }
}
