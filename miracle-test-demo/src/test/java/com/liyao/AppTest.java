package com.liyao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@Slf4j
@SpringBootTest
public class AppTest {

    @BeforeAll
    public static void init() {
        log.info("init");
    }

    @BeforeEach
    public void initEach() {
        log.info("initEach");
    }

    @Test
    public void testAssertEquals() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(10, array.length);
    }

    @Test
    public void testAssertEx() {
        Function<Integer, Integer> func = i -> {
            if (i > 10) {
                throw new RuntimeException();
            }
            return 1;
        };
        assertThrows(RuntimeException.class, () -> func.apply(11));
    }
}
