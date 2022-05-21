package com.abc.module.te.prod.cate;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeProdCateService extends BaseMainService<TeProdCate> {


    //----------bean注入------------
    @Autowired
    private TeProdCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
