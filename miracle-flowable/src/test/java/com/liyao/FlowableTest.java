package com.liyao;

import org.flowable.engine.RepositoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = ConsoleApplication.class)
public class FlowableTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void deploy() {
        repositoryService.createDeployment()
                .addClasspathResource("flowable-test4.bpmn20.xml")
                .deploy();
    }
}
