package com.abc.test.activiti;

import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.Execution;
import org.apache.catalina.security.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Part10_EventAndTask {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private ProcessRuntime processRuntime;
    @Test
    public void signalStart() {
        runtimeService.signalEventReceived("Signal_0igedde");
    }

    @Test
    public void msgBack() {
        Execution exec = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("Message_2qvor1p")
                .processInstanceId("618bdd31-ef41-11ea-854b-dcfb4875e032")
                .singleResult();
        runtimeService.messageEventReceived("Message_2qvor1p",exec.getId());

       // runtimeService.startProcessInstanceByMessage("Message_2qvor1p");

//        ProcessInstance pi = processRuntime.start(StartMessagePayloadBuilder
//                        .start("Message_2qvor1p")
//                .build()
//                );

    }


}
