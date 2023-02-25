package com.liyao.miracle.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @Author 栗垚
 * @Date 2023/1/8
 */
@Slf4j
@SpringBootTest(classes = com.liyao.miracle.test.ATest.class)
public class ATest {

    @Test
    public void test1() {
        BigDecimal d1 = new BigDecimal("1.1");
        BigDecimal d2 = new BigDecimal("1.10");

        assertEquals(0, d1.compareTo(d2));
        assertNotEquals(d1, d2);
    }

    /**
     * @author xxx
     * @date 2021/11/26
     **/
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);

        // 重复key抛出异常:java.lang.IllegalStateException: Duplicate key
//        Map<Integer, Integer> map1 = list.stream().collect(toMap(t -> t, t -> t));
        // 应该指定key冲突时的处理策略
        Map<Integer, Integer> map2 = list.stream().collect(toMap(t -> t, t -> t, (o1, o2) -> o1));
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }

        for (String intt : list) {
            System.out.println(intt);
        }
    }

    @Test
    public void test4() {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        if (a == b) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 a == b 的结果为 false
        }
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 equals 的结果为 false
        }
    }
}