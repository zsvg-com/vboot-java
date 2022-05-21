package com.abc.module.te.feat.link;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeFeatLinkService extends BaseMainService<TeFeatLink> {



    //----------bean注入------------
    @Autowired
    private TeFeatLinkRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
