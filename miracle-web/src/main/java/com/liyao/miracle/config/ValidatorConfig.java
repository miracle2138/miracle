package com.liyao.miracle.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Properties;

import javax.validation.Validator;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@Configuration
public class ValidatorConfig {

    @Autowired
    private MessageSource messageSource;
    
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        // 设置上方配置的国际化源
        factoryBean.setValidationMessageSource(messageSource);
        // 设置使用 HibernateValidator 校验器
        factoryBean.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        // 设置 快速异常返回
        properties.setProperty("hibernate.validator.fail_fast", "true");
        factoryBean.setValidationProperties(properties);
        // 加载配置
        factoryBean.afterPropertiesSet();
        return factoryBean.getValidator();
    }
}
