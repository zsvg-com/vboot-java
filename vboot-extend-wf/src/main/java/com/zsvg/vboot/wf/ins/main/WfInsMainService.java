package com.zsvg.vboot.wf.ins.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class WfInsMainService extends BaseMainService<WfInsMain> {








    //----------bean注入------------
    @Autowired
    private WfInsMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

