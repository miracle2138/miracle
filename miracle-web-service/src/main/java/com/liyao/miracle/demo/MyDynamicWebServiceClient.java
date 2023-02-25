package com.liyao.miracle.demo;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/1/1
 */
@Slf4j
public class MyDynamicWebServiceClient {

    public static void main(String[] args) throws Exception {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/services/testService?wsdl");
        Object[] objects = new Object[0];
        // invoke("方法名",参数1,参数2,参数3....);
        objects = client.invoke("queryById", 123L);
        log.info("返回数据: {}", objects[0]);
    }
}
