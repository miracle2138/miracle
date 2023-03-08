package com.liyao;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Hello world!
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoService.print();
    }
}
