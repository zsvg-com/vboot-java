package com.zsvg.vboot.module.ass.dict.data;

import com.zsvg.vboot.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class AssDictDataService extends BaseService<AssDictData> {








    //----------bean注入------------
    @Autowired
    private AssDictDataRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

