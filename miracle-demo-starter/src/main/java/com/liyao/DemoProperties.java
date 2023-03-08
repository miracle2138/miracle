package com.liyao;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author 栗垚
 * @Date 2023/3/2
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
