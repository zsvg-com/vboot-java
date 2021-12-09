package com.zsvg.vboot.it.server.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItServerMainService extends BaseMainService<ItServerMain> {


    //----------bean注入------------
    @Autowired
    private ItServerMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}

