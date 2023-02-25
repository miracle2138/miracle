package com.liyao.miracle.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author 栗垚
 * @Date 2022/12/31
 */
// http://localhost:8080/services/testService?wsdl
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebserviceApplication.class, args);
    }
}