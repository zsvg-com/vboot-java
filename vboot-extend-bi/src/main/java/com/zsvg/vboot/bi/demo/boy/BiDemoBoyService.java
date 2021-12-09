package com.zsvg.vboot.bi.demo.boy;

import com.zsvg.vboot.common.mvc.service.BaseService;
import com.zsvg.vboot.it.db.main.ItDbMainRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class BiDemoBoyService extends BaseService<BiDemoBoy> {

    //----------bean注入------------
    @Autowired
    private BiDemoBoyRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
