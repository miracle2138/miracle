package com.bamboocloud.oauth.sso.controller;

import com.bamboocloud.oauth.sso.service.IdpAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IdpAuthService authService;

    @Value("${app.bjam.oauth2.idp.glo.url}")
    private String globalLogoutUrl;

    @Value("${app.bjam.oauth2.entity.id}")
    private String entityId;

    @GetMapping({"/", "index"})
    public String index(HttpServletRequest request, Map<String, Object> model) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            model.put("globalLogoutUrl", globalLogoutUrl);
            model.put("entityId", entityId);
            String accessToken =  ((Map) session.getAttribute("authorizationInfo")).get("access_token").toString();
            Map<String, Object> userInfoMap = authService.getUserInfo(accessToken);
            model.put("userInfo", userInfoMap);
            model.put("displayName", userInfoMap.get("displayName"));
            model.put("loginName", userInfoMap.get("loginName"));
        }
        return "index";
    }

    @GetMapping("/error")
    public String error(Map<String, Object> model) {
        model.put("timestamp", new Timestamp(new Date().getTime()));
        model.put("error", "error");
        model.put("status", "400");
        model.put("message", "message");
        return "error";
    }

}
