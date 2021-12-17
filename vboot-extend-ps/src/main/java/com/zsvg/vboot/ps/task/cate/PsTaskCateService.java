package com.zsvg.vboot.ps.task.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PsTaskCateService extends BaseCateService<PsTaskCate> {


    //----------bean注入------------
    @Autowired
    private PsTaskCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
