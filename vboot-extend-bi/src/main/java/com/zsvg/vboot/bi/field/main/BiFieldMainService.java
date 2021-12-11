package com.zsvg.vboot.bi.field.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BiFieldMainService extends BaseMainService<BiFieldMain> {

    //----------bean注入------------
    @Autowired
    private BiFieldMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


