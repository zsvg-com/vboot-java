package com.abc.module.sa.draw.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SaDrawMainService extends BaseMainService<SaDrawMain> {



    //----------bean注入------------
    @Autowired
    private SaDrawMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
