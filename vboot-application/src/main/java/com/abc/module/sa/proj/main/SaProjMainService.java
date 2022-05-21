package com.abc.module.sa.proj.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class SaProjMainService extends BaseMainService<SaProjMain> {


    //----------bean注入------------
    @Autowired
    private SaProjMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

