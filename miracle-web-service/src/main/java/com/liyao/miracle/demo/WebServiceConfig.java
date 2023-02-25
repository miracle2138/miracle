package com.liyao.miracle.demo;

import com.liyao.miracle.demo.service.MyDemoService;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Author 栗垚
 * @Date 2022/12/31
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private MyDemoService demoService;

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(springBus(), demoService);
        endpoint.publish("/testService"); // 这个路径是wsdl访问的路径
        return endpoint;
    }
}
