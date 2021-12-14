package com.zsvg.vboot.wf.tem.cate;

import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class WfTemCateService extends BaseCateService<WfTemCate> {






    //----------bean注入------------
    @Autowired
    private WfTemCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
