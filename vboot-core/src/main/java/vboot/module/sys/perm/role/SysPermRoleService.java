package vboot.module.sys.perm.role;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SysPermRoleService extends BaseMainService<SysPermRole> {

    //bean注入------------------------------
    @Autowired
    private SysPermRoleRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
