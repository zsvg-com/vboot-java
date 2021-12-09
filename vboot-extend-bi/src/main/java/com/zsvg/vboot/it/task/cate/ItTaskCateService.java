package com.zsvg.vboot.it.task.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ItTaskCateService extends BaseCateService<ItTaskCate> {


    //----------bean注入------------
    @Autowired
    private ItTaskCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
