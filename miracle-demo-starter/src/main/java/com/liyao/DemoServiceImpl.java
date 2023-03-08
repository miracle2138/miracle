package com.liyao;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/3/2
 */
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DemoProperties demoProperties;

    public DemoServiceImpl(DemoProperties demoProperties) {
        this.demoProperties = demoProperties;
    }

    @Override
    public void print() {
        log.info("name: {}, age: {}", demoProperties.getName(), demoProperties.getAge());
    }
}
