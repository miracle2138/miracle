package com.miracle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author 栗垚
 * @Date 2023/3/18
 * <p>
 * swagger2 配置
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                // apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage("com.csp.mingyue.swagger3.controller"))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3 测试接口文档")
                .description("【接口篇】SpringBoot 整合 Swagger3 实现在线 API 文档")
                .contact(
                        new Contact(
                                "Strive", "https://gitee.com/csps/mingyue-springboot-learning", "732171109@qq.com"))
                .version("3.0")
                .build();
    }
}
//@Configuration
//public class Swagger2Config {
//
//    @Bean
//    public Docket customDocket() {
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        ticketPar.name("Authorization").description("认证token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        pars.add(ticketPar.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .build()
//                .globalOperationParameters(pars)
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                //页面标题
//                .title("demo API")
//                //创建人
//                .contact(new Contact("研发中心-栗垚", "", ""))
//                //版本号
//                .version("1.0")
//                //描述
//                .description("REST API")
//                .build();
//    }
}