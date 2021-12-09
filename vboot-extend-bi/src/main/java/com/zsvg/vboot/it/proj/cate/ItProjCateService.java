package com.zsvg.vboot.it.proj.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItProjCateService extends BaseCateService<ItProjCate> {


    //----------bean注入------------
    @Autowired
    private ItProjCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
