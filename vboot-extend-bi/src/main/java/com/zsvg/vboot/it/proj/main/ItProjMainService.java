package com.zsvg.vboot.it.proj.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItProjMainService extends BaseMainService<ItProjMain> {

    //----------bean注入------------
    @Autowired
    private ItProjMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


