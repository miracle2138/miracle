package com.liyao.miracle.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @Author 栗垚
 * @Date 2022/11/22
 */
@Configuration
public class I18nConfig {

    @Bean("messageSource")
    public MessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new I18nLocaleResolver();
    }

    /**
     * 获取请求头国际化信息
     */
    static class I18nLocaleResolver implements LocaleResolver {

        @NotNull
        @Override
        public Locale resolveLocale(HttpServletRequest httpServletRequest) {
            String language = httpServletRequest.getHeader("content-language");
            Locale locale = Locale.getDefault();
            if (StringUtils.isNotBlank(language)) {
                String[] split = language.split("_");
                locale = new Locale(split[0], split[1]);
            }
            return locale;
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }
}