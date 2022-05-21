package com.abc.module.oa.doc.main;

import vboot.common.mvc.service.BaseMainService;
import vboot.module.bpm.proc.main.BpmProcMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class OaDocMainService extends BaseMainService<OaDocMain> {

    public OaDocMain insert(OaDocMain OaDocMain)  {
        OaDocMain dbMain = super.insert(OaDocMain);
//        BpmProcMain wfFlowMain = new BpmProcMain(dbMain);
//        flowService.start(wfFlowMain);
        return dbMain;
    }

    //----------bean注入------------
    @Autowired
    private BpmProcMainService flowService;

    @Autowired
    private OaDocMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
