package vboot.module.bpm.proc.temp;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BpmProcTempService extends BaseMainService<BpmProcTemp> {

    //----------bean注入------------
    @Autowired
    private BpmProcTempRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
