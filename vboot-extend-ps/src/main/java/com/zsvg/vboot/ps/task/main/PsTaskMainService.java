package com.zsvg.vboot.ps.task.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PsTaskMainService extends BaseMainService<PsTaskMain> {

    //----------bean注入------------
    @Autowired
    private PsTaskMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}


