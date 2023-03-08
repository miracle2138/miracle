package com.liyao;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 栗垚
 * @Date 2023/2/24
 */
@Slf4j
@SpringBootApplication
@Component
public class ConsoleApplication implements CommandLineRunner {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        run2();
    }

    private void run2() {
        repositoryService.createDeployment()
                .addClasspathResource("flowable-test3.bpmn20.xml")
                .deploy();
        runtimeService.startProcessInstanceByKey("third_flow");
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("ly").list();
        log.info("ly tasks.size: {}", tasks.size());
        tasks.get(0).setAssignee("ll");
        taskService.saveTask(tasks.get(0));
        taskService.complete(taskService.createTaskQuery().taskAssignee("ll").list().get(0).getId());
        List<Task> tasks1 = taskService.createTaskQuery().taskCandidateOrAssigned("ly1").list();
        log.info("ly1 tasks.size: {}", tasks.size());

        List<Task> tasks2 = taskService.createTaskQuery().taskCandidateOrAssigned("ly2").list();
        log.info("ly2 tasks.size: {}", tasks.size());
    }

    private void run1() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("flowable-test2.bpmn20.xml")
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        Map<String, Object> params = new HashMap<>();
        params.put("k1", "v1");
        ProcessInstance process = runtimeService.startProcessInstanceByKey("second_key", params);
        List<Task> tasks1 = taskService.createTaskQuery().taskCandidateOrAssigned("ly").list();
        List<Task> tasks2 = taskService.createTaskQuery().taskCandidateOrAssigned("lzy").list();
        log.info("tasks1: {}", tasks1);
        log.info("tasks2: {}", tasks2);

        taskService.complete(tasks1.get(0).getId());
        tasks1 = taskService.createTaskQuery().taskCandidateOrAssigned("ly").list();
        tasks2 = taskService.createTaskQuery().taskCandidateOrAssigned("lzy").list();
        log.info("tasks1: {}", tasks1);
        log.info("tasks2: {}", tasks2);
    }
}
