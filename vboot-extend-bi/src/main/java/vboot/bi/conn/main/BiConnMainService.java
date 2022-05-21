package vboot.bi.conn.main;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BiConnMainService extends BaseMainService<BiConnMain> {

    //----------bean注入------------
    @Autowired
    private BiConnMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
