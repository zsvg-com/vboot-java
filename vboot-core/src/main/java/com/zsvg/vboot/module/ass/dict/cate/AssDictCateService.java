package com.zsvg.vboot.module.ass.dict.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AssDictCateService extends BaseCateService<AssDictCate> {






    //----------bean注入------------
    @Autowired
    private AssDictCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
