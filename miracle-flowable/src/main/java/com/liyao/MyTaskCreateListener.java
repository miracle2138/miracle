package com.liyao;

import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import lombok.extern.slf4j.Slf4j;

import static java.util.Optional.ofNullable;

/**
 * @Author 栗垚
 * @Date 2023/2/25
 */
@Slf4j
public class MyTaskCreateListener implements TaskListener {
    private Expression f1, f2;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("f1: {}", ofNullable(f1).map(f -> f.getValue(delegateTask)).orElse("null"));
        log.info("f2: {}", ofNullable(f2).map(f -> f.getValue(delegateTask)).orElse("null"));
        log.info("delegateTask: {}", delegateTask);
    }
}
