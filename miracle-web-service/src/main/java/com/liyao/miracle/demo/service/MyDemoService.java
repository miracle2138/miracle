package com.liyao.miracle.demo.service;

import com.liyao.miracle.demo.model.DemoEntity;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Author 栗垚
 * @Date 2022/12/31
 */
@WebService(targetNamespace = "https://service.demo.miracle.liyao.com")
public interface MyDemoService {

    @WebMethod
    DemoEntity queryById(@WebParam(name = "id") Long id);
}
