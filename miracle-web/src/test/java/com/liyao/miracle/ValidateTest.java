package com.liyao.miracle;

import com.liyao.miracle.validationDemo.model.QueryParamDemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@Slf4j
@SpringBootTest
public class ValidateTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void test() {
        QueryParamDemo demo = QueryParamDemo.builder()
                .id(100L)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<QueryParamDemo>> result = validator.validate(demo);

        result.forEach(
                q -> log.info("{}", q.getMessage())
        );
    }

    @Test
    public void testMessage() {
        String m1 = messageSource.getMessage("k.v.message", null, "default", Locale.getDefault());
        log.info("m1: {}", m1);

        String m2 = messageSource.getMessage("k.v1.message", new String[]{"h1", "h2"}, "default", Locale.getDefault());
        log.info("m2: {}", m2);
    }
}
