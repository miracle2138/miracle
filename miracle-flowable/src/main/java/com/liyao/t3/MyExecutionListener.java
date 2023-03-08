package com.liyao.t3;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/2/27
 */
@Slf4j
public class MyExecutionListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        log.info("MyExecutionListener.notify()");
        log.info("delegateExecution: {}", delegateExecution);
    }
}
