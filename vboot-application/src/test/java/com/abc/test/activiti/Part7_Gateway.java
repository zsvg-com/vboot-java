package com.abc.test.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Part7_Gateway {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Test
    public void initProcessInstance() {

        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(
                        "myProcess_Inclusive");
        System.out.println("流程实例ID："+processInstance.getProcessDefinitionId());
    }

    @Test
    public void completeTask() {

        Map<String, Object> variables = new HashMap<String, Object>();

        //流程实例idecd41693-d3cd-11ea-ad34-dcfb4875e032

        taskService.complete("398a746e-d3ce-11ea-8bb4-dcfb4875e032");
        taskService.complete("398a7470-d3ce-11ea-8bb4-dcfb4875e032");
        System.out.println("完成任务");


    }

}
