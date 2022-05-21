package vboot.module.ass.dict.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class AssDictMainService extends BaseMainService<AssDictMain> {








    //----------bean注入------------
    @Autowired
    private AssDictMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

