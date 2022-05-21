package com.abc.module.sa.agent.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vboot.common.mvc.service.BaseMainService;

import javax.annotation.PostConstruct;

@Service
public class SaAgentMainService extends BaseMainService<SaAgentMain> {


    //----------bean注入------------
    @Autowired
    private SaAgentMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
