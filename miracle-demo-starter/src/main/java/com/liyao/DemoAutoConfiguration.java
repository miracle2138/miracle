package com.liyao;

import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 栗垚
 * @Date 2023/3/2
 */
@Configuration
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfiguration {

    @Resource
    private DemoProperties demoProperties;

    @Bean
    @ConditionalOnMissingBean(DemoService.class)
    public DemoService demoService() {
        return new DemoServiceImpl(demoProperties);
    }
}
