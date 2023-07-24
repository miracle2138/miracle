package com.bamboocloud.oauth.sso.controller;

import com.bamboocloud.oauth.sso.service.IdpAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("logout")
public class LogoutController {

    private Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private IdpAuthService authService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        /*if(session != null) {
            Map<String, Object> authorizationInfoMap = (Map) session.getAttribute("authorizationInfo");
            if(authorizationInfoMap != null) {
                String accessToken = authorizationInfoMap.get("access_token").toString();
                if(authService.revokeToken(accessToken)) {
                    logger.info("成功退出登录");
                    session.invalidate();
                }
            }
        }*/
        if(session != null) {
            session.invalidate();
        }
        return "redirect:login";
    }
}
