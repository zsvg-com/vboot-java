package com.abc.module.sa.prod.tem;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SaProdTemService extends BaseMainService<SaProdTem> {


    //----------bean注入------------
    @Autowired
    private SaProdTemRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
