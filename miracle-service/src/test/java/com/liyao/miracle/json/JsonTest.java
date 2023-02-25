package com.liyao.miracle.json;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

import static com.liyao.miracle.json.JsonUtils.fromJson;

/**
 * @Author 栗垚
 * @Date 2023/1/10
 */
@Slf4j
@SpringBootTest(classes = com.liyao.miracle.json.JsonTest.class)
public class JsonTest {

    @Test
    public void test() {
        Demo demo = new Demo();
        demo.isSuccess = true;
        String json = JsonUtils.toJson(demo);
        log.info("json: {}", json);
    }

    @Test
    public void test1() {
        String json = "{\"success\":true}";
        Demo demo = fromJson(json, Demo.class);
        log.info("success: {}", demo.isSuccess);
    }

    static class Demo {
        boolean isSuccess;

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setSuccess(boolean success) {
            isSuccess = success;
        }
    }
}
