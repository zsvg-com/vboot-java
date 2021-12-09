package com.zsvg.vboot.bi.demo.girl;

import com.zsvg.vboot.bi.demo.boy.BiDemoBoyRepo;
import com.zsvg.vboot.common.mvc.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class BiDemoGirlService extends BaseService<BiDemoGirl> {

    //----------bean注入------------
    @Autowired
    private BiDemoGirlRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
