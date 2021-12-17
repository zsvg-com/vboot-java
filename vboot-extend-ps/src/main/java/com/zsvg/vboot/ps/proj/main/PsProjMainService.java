package com.zsvg.vboot.ps.proj.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PsProjMainService extends BaseMainService<PsProjMain> {

    //----------bean注入------------
    @Autowired
    private PsProjMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


