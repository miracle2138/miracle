package com.bamboocloud.oauth.sso.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.bjam.oauth2.client.id}")
    private String clientId;

    @Value("${app.bjam.oauth2.idp.head.url}")
    private String headUrl;

    @Value("${app.bjam.oauth2.redirect.url}")
    private String redirectURI;

    @Value("${app.bjam.oauth2.response.type}")
    private String responseType;

    @Value("${app.bjam.oauth2.state}")
    private String state;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String leadToIdpLogin(Map<String, Object> model) {
        StringBuffer sb = new StringBuffer();
        sb.append(headUrl).append("/authorize").append("?")//跳转到统一身份认证页面
//        sb.append(headUrl).append("/authenticate").append("?")//用户名密码方式认证
                .append("client_id=").append(clientId).append("&")
                .append("redirect_uri=").append(redirectURI).append("&")
                .append("response_type=").append(responseType).append("&")
                .append("state=").append(state);
        String leadToIdpLoginUrl = sb.toString();
        logger.info("leadToIdpLoginUrl:{}", leadToIdpLoginUrl);
        return "redirect:" + leadToIdpLoginUrl;
    }
}
