package com.liyao.miracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 栗垚
 * @Date 2022/10/29
 * @Description
 */
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