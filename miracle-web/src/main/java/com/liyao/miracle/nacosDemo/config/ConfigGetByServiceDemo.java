package com.liyao.miracle.nacosDemo.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Author 栗垚
 * @Date 2022/11/21
 */
@Component
public class ConfigGetByServiceDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConfigGetByServiceDemo.class);

    public void test() throws NacosException {
        String serverAddr = "127.0.0.1";
        String dataId = "liyao.test.json";
        String group = "app1";
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, "8b6860a9-092d-40a3-94c0-2f07b9c51f82");
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        logger.info("content: {}", content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                logger.info("receive: {}", configInfo);
            }
        });
    }
}
