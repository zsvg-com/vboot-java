package vboot.module.bpm.audit.main;


import vboot.module.bpm.proc.main.Zbpm;
import vboot.module.bpm.proc.main.Znode;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmAuditMainService {

    public BpmAuditMain saveDraftAudit(Zbpm zbpm, Znode znode) {
        BpmAuditMain audit = new BpmAuditMain();
        audit.setId(XstringUtil.getUUID());
        audit.setFacno(znode.getFacno());
        audit.setFacna(znode.getFacna());
        audit.setNodid(znode.getNodid());
        audit.setHaman(zbpm.getHaman());
        audit.setProid(zbpm.getProid());
        audit.setOpnot(zbpm.getOpnot());
        audit.setOpkey("dsubmit");
        audit.setOpinf("起草人提交");
        return repo.save(audit);
    }

    public BpmAuditMain saveAudit(Zbpm zbpm) {
        BpmAuditMain audit = new BpmAuditMain();
        audit.setId(XstringUtil.getUUID());
        audit.setFacno(zbpm.getFacno());
        audit.setFacna(zbpm.getFacna());
        audit.setNodid(zbpm.getNodid());
        audit.setHaman(zbpm.getHaman());
        audit.setProid(zbpm.getProid());
        audit.setOpnot(zbpm.getOpnot());
        audit.setOpkey(zbpm.getOpkey());
        audit.setOpinf(zbpm.getOpinf());
        audit.setTasid(zbpm.getTasid());
        return repo.save(audit);
    }

    public BpmAuditMain saveEndAudit(Zbpm zbpm, String nodid) {
        BpmAuditMain audit = new BpmAuditMain();
        audit.setId(XstringUtil.getUUID());
        audit.setFacno("N3");
        audit.setFacna("结束节点");
        audit.setNodid(nodid);
        audit.setProid(zbpm.getProid());
        audit.setOpkey("end");
        return repo.save(audit);
    }




    @Autowired
    private BpmAuditMainRepo repo;
}
