package vboot.bi.table.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BiTableMainService extends BaseMainService<BiTableMain> {

    //----------bean注入------------
    @Autowired
    private BiTableMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


