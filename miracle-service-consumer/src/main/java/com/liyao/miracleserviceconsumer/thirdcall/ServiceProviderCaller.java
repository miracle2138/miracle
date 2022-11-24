package com.liyao.miracleserviceconsumer.thirdcall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 栗垚
 * @Date 2022/11/24
 */
@FeignClient(name = "miracle-service-provider")
public interface ServiceProviderCaller {
    @RequestMapping("/service/provider")
    String callServiceProvider();
}
