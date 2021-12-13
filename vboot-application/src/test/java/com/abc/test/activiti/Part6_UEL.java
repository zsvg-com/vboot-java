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
public class Part6_UEL {


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    //启动流程实例带参数，执行执行人
    @Test
    public void initProcessInstanceWithArgs() {
        //流程变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("ZhiXingRen", "wukong");
        //variables.put("ZhiXingRen2", "aaa");
        //variables.put("ZhiXingRen3", "wukbbbong");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(
                        "myProcess_UEL_V1"
                        , "bKey002"
                        , variables);
        System.out.println("流程实例ID：" + processInstance.getProcessDefinitionId());

    }

    //完成任务带参数，指定流程变量测试
    @Test
    public void completeTaskWithArgs() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("pay", "101");
        taskService.complete("a616ea19-d3a7-11ea-9e14-dcfb4875e032",variables);
        System.out.println("完成任务");
    }

    //启动流程实例带参数，使用实体类
    @Test
    public void initProcessInstanceWithClassArgs() {
        UEL_POJO uel_pojo = new UEL_POJO();
        uel_pojo.setZhixingren("bajie");

        //流程变量
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("uelpojo", uel_pojo);

        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(
                        "myProcess_uelv3"
                        , "bKey002"
                        , variables);
        System.out.println("流程实例ID：" + processInstance.getProcessDefinitionId());

    }

    //任务完成环节带参数，指定多个候选人
    @Test
    public void initProcessInstanceWithCandiDateArgs() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("houxuanren", "wukong,tangseng");
        taskService.complete("4f6c9e23-d3ae-11ea-82ba-dcfb4875e032",variables);
        System.out.println("完成任务");
    }

    //直接指定流程变量
    @Test
    public void otherArgs() {
        runtimeService.setVariable("4f6c9e23-d3ae-11ea-82ba-dcfb4875e032","pay","101");
//        runtimeService.setVariables();
//        taskService.setVariable();
//        taskService.setVariables();

    }

    //局部变量
    @Test
    public void otherLocalArgs() {
        runtimeService.setVariableLocal("4f6c9e23-d3ae-11ea-82ba-dcfb4875e032","pay","101");
//        runtimeService.setVariablesLocal();
//        taskService.setVariableLocal();
//        taskService.setVariablesLocal();
    }

}
