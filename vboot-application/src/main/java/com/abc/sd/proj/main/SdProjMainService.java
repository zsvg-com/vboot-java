package com.abc.sd.proj.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class SdProjMainService extends BaseMainService<SdProjMain> {






    //----------bean注入------------
    @Autowired
    private SdProjMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

