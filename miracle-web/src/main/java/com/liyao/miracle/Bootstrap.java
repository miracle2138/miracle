package com.liyao.miracle;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 栗垚
 * @Date 2022/10/29
 * @Description
 */
@NacosPropertySources({
        @NacosPropertySource(dataId = "liyao.test.string", groupId = "app1", autoRefreshed = true),
        @NacosPropertySource(dataId = "liyao.test.string1", groupId = "app1", autoRefreshed = true)
})
@SpringBootApplication
public class Bootstrap {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Bootstrap.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}





