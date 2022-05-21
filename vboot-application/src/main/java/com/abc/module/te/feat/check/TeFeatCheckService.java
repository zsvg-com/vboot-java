package com.abc.module.te.feat.check;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeFeatCheckService extends BaseMainService<TeFeatCheck> {


    //----------bean注入------------
    @Autowired
    private TeFeatCheckRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
