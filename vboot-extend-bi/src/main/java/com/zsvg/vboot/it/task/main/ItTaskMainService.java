package com.zsvg.vboot.it.task.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItTaskMainService extends BaseMainService<ItTaskMain> {

    //----------bean注入------------
    @Autowired
    private ItTaskMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


