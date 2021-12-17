package com.abc.it.server.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItServerCateService extends BaseCateService<ItServerCate> {


    //----------bean注入------------
    @Autowired
    private ItServerCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
