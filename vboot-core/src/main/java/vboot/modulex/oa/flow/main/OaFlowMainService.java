package vboot.modulex.oa.flow.main;

import vboot.common.mvc.service.BaseMainService;
import vboot.module.bpm.proc.main.BpmProcMainService;
import vboot.module.bpm.proc.main.Zbpm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vboot.module.bpm.proc.main.Znode;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class OaFlowMainService extends BaseMainService<OaFlowMain> {

    public void insertx(OaFlowMain oaFlowMain) {
        oaFlowMain.setState("20");
        oaFlowMain.setUptim(new Date());
        OaFlowMain dbMain = super.insert(oaFlowMain);

        Zbpm zbpm = oaFlowMain.getZbpm();
        zbpm.setProid(dbMain.getId());
        zbpm.setProna(dbMain.getName());
        zbpm.setHaman(dbMain.getCrman().getId());
        zbpm.setTemid(dbMain.getProtd());
        Znode znode = procService.start(zbpm);
        if ("N3".equals(znode.getFacno())) {
            dbMain.setState("30");
            super.update(dbMain);
        }
    }

    public void updatex(OaFlowMain oaFlowMain) {
        super.update(oaFlowMain);
        if ("pass".equals(oaFlowMain.getZbpm().getOpkey())) {
            Znode znode = procService.handlerPass(oaFlowMain.getZbpm());
            if ("N3".equals(znode.getFacno())) {
                oaFlowMain.setState("30");
                super.update(oaFlowMain);
            }
        } else if ("refuse".equals(oaFlowMain.getZbpm().getOpkey())) {
            Znode znode =  procService.handlerRefuse(oaFlowMain.getZbpm());
        }
    }

    //----------bean注入------------
    @Autowired
    private BpmProcMainService procService;

    @Autowired
    private OaFlowMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
