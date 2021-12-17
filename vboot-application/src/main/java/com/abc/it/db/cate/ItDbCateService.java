package com.abc.it.db.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItDbCateService extends BaseCateService<ItDbCate> {


    //----------bean注入------------
    @Autowired
    private ItDbCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
