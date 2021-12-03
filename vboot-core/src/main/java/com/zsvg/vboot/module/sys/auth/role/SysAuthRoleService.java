package com.zsvg.vboot.module.sys.auth.role;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SysAuthRoleService extends BaseMainService<SysAuthRole> {

    //bean注入------------------------------
    @Autowired
    private SysAuthRoleRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
