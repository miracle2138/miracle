package com.liyao.controller;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/2/28
 */
@Slf4j
@RestController
@RequestMapping("/flow")
public class DemoController {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @RequestMapping("/deploy")
    public String deploy() {
        return repositoryService.createDeployment()
                .addClasspathResource("flow/flowable-test1.bpmn20.xml")
                .deploy()
                .getId();
    }

    @RequestMapping("/startProcess")
    public String start() {
        log.info("start");
        return runtimeService.startProcessInstanceByKey("key4")
                .getId();
    }

    @RequestMapping("/completeTask")
    public String completeTask(@RequestParam String taskId) {
        taskService.complete(taskId);
        return "success";
    }
}
