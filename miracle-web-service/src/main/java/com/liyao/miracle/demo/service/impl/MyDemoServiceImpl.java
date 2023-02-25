package com.liyao.miracle.demo.service.impl;

import com.liyao.miracle.demo.model.DemoEntity;
import com.liyao.miracle.demo.service.MyDemoService;

import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author 栗垚
 * @Date 2022/12/31
 */
@Service
@WebService(serviceName = "testService", // 服务名，和wsdl访问地址无关
        targetNamespace = "https://service.demo.miracle.liyao.com", // 接口的包名倒序
        endpointInterface = "com.liyao.miracle.demo.service.MyDemoService") // 接口类名
public class MyDemoServiceImpl implements MyDemoService {
    @Override
    public DemoEntity queryById(@WebParam(name = "id") Long id) {
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setName("ly");
        return demoEntity;
    }
}
