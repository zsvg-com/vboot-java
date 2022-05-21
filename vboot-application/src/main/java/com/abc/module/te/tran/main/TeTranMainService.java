package com.abc.module.te.tran.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeTranMainService extends BaseMainService<TeTranMain> {


    //----------bean注入------------
    @Autowired
    private TeTranMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
