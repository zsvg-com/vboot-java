package com.abc.module.te.prod.test;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeProdTestService extends BaseMainService<TeProdTest> {





    //----------bean注入------------
    @Autowired
    private TeProdTestRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
