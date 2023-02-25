package com.liyao.miracle.demo;

import com.liyao.miracle.demo.model.DemoEntity;
import com.liyao.miracle.demo.service.MyDemoService;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/1/1
 */
@Slf4j
public class MyWebServiceClient {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://localhost:8080/services/testService?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(MyDemoService.class);

        MyDemoService myDemoService = (MyDemoService) jaxWsProxyFactoryBean.create();
        DemoEntity demoEntity = myDemoService.queryById(123L);
        log.info("UserName: {}", demoEntity.getName());
    }
}
