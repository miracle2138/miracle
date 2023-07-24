package com.bamboocloud.oauth.sso.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bamboocloud.oauth.sso.core.Result;
import com.bamboocloud.oauth.sso.core.ResultCode;
import com.bamboocloud.oauth.sso.core.ServiceException;
import com.bamboocloud.oauth.sso.service.IdpAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Autowired
    private IdpAuthService authService;

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);//保留空的字段
        config.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty); //String null -> ""
        config.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        // 按需配置，更多参考FastJson文档
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                String message = null;
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    message = e.getMessage();
                    result.setCode(ResultCode.FAIL).setMessage(message);
                    logger.error(message);
                } else if (e instanceof NoHandlerFoundException) {
                    message = "接口 [" + request.getRequestURI() + "] 不存在";
                    result.setCode(ResultCode.NOT_FOUND).setMessage(message);
                    logger.warn(message);
                } else if (e instanceof ServletException) {
                    message = e.getMessage();
                    result.setCode(ResultCode.FAIL).setMessage(message);
                    logger.warn(message);
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 验证签名
        InterceptorRegistration addInterceptor = registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HttpSession session = request.getSession(true);

                Map<String, Object> authorizationInfoMap = (Map) session.getAttribute("authorizationInfo");
                if(authorizationInfoMap == null) {
                    String code = request.getParameter("code");
                    if(code != null) {
                        logger.info("根据授权码获取授权令牌,code={}", code);
                        authorizationInfoMap = authService.getToken(code);
                        String accessToken = authorizationInfoMap.get("access_token").toString();

                        // 检查token是否有效
                        if(authService.checkTokenValid(accessToken)) {
                            // 将授权信息放到session里面
                            session.setAttribute("authorizationInfo", authorizationInfoMap);
                            logger.info("成功获取access_token");
                            return true;
                        }
                    }
                    response.sendRedirect(request.getContextPath() + "/login");
                    return false;
                }

                // 校验token
                String accessToken = authorizationInfoMap.get("access_token").toString();
                boolean pass = authService.checkTokenValid(accessToken);
                if (pass) {
                    logger.info("token校验通过");
                    return true;
                }
                logger.warn("令牌校验失败，请求接口：{}，请求参数：{}",
                        request.getRequestURI(), JSON.toJSONString(request.getParameterMap()));

                logger.info("尝试刷新token");
                String refreshToken = authorizationInfoMap.get("refresh_token").toString();
                Map<String, Object> refreshTokenAuthorizationInfoMap = new HashMap<>();
                try {
                    refreshTokenAuthorizationInfoMap = authService.refreshToken(refreshToken);
                    session.setAttribute("authorizationInfo", refreshTokenAuthorizationInfoMap);
                    logger.info("刷新token成功");
                    return true;
                } catch (ServiceException e) {
                    if("1025".equals(e.getMessage())) {
                        logger.warn("参数refresh_token不正确或过期");
                        response.sendRedirect(request.getContextPath() + "/login");
                        return false;
                    } else {
                        logger.error(e.toString());
                        throw e;
                    }
                }
            }
        });
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**");
        addInterceptor.excludePathPatterns("/login/*");
        addInterceptor.excludePathPatterns("/logout**");
        addInterceptor.excludePathPatterns("/logout/*");
        addInterceptor.addPathPatterns("/**");
    }

   /* private boolean checkTokenValid(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("authorizationInfo") == null) {
            return false;
        }
        Map<String, Object> authorizationInfoMap = (Map) session.getAttribute("authorizationInfo");
        return authService.checkTokenValid(authorizationInfoMap.get("access_token").toString());
    }*/
}
