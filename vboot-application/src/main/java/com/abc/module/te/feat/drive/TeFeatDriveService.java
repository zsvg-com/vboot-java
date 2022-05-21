package com.abc.module.te.feat.drive;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeFeatDriveService extends BaseMainService<TeFeatDrive> {


    //----------bean注入------------
    @Autowired
    private TeFeatDriveRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
