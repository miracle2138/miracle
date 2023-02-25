package com.liyao;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @Author 栗垚
 * @Date 2023/2/23
 */
public class CallExternalSystemDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println("Calling the external system for employee "
                + execution.getVariable("employee"));
    }
}
