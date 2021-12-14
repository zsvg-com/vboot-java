package com.zsvg.vboot.wf.tem.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class WfTemMainService extends BaseMainService<WfTemMain> {








    //----------bean注入------------
    @Autowired
    private WfTemMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

