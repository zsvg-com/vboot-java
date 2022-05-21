package com.abc.module.te.tran.cate;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeTranCateService extends BaseMainService<TeTranCate> {


    //----------bean注入------------
    @Autowired
    private TeTranCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
