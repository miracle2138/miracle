package com.liyao.t3;

import com.liyao.miracle.utils.BeanUtils;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/2/27
 */
@Slf4j
@Component
public class MyTaskCompleteListener implements TaskListener {
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("MyTaskCompleteListener.notify()");
        BeanUtils.getBean(RuntimeService.class).signalEventReceived("信号1");
//        runtimeService.messageEventReceived("消息1", delegateTask.getExecutionId());
    }
}
