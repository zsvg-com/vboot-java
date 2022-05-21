package com.abc.module.sa.prod.group;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class SaProdGroupService extends BaseMainService<SaProdGroup> {

    public List<SaProdGroup> findAllByProjid(String projid){
        return repo.findAllByProjid(projid);
    }

    //----------bean注入------------
    @Autowired
    private SaProdGroupRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

