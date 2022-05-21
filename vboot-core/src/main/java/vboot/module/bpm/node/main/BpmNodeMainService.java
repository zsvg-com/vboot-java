package vboot.module.bpm.node.main;


import vboot.common.util.lang.XstringUtil;
import vboot.module.bpm.proc.main.Zbpm;
import vboot.module.bpm.proc.main.Znode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmNodeMainService {

    public BpmNodeMain saveNode(Zbpm zbpm, Znode znode) {
        BpmNodeMain node = new BpmNodeMain();
        node.setFacno(znode.getFacno());
        node.setFacna(znode.getFacna());
        node.setFacty(znode.getFacty());
        node.setProid(zbpm.getProid());
        node.setState("20");
        node.setId(XstringUtil.getUUID());
        return repo.save(node);
    }

    @Transactional(readOnly = true)
    public BpmNodeMain findOne(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    @Autowired
    private BpmNodeMainRepo repo;
}
