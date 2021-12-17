package com.abc.it.db.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItDbMainService extends BaseMainService<ItDbMain> {

    //----------bean注入------------
    @Autowired
    private ItDbMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


