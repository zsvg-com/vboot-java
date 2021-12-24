package com.zsvg.vboot.ps.proj.stage;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PsProjStageService extends BaseMainService<PsProjStage> {


    //----------bean注入------------
    @Autowired
    private PsProjStageRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}