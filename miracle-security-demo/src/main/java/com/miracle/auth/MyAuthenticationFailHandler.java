package com.miracle.auth;

import com.spicdt.formula.framework.common.util.json.JsonUtils;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.extra.servlet.ServletUtil;

/**
 * @Author 栗垚
 * @Date 2023/3/22
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        String content = JsonUtils.toJsonString("hello");
        ServletUtil.write(response, content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
