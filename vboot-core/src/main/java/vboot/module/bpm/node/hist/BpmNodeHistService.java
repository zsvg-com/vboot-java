package vboot.module.bpm.node.hist;

import vboot.common.util.lang.XstringUtil;
import vboot.module.bpm.node.main.BpmNodeMain;
import vboot.module.bpm.proc.main.Zbpm;
import vboot.module.bpm.proc.main.Znode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmNodeHistService {

    public void saveStartNode(Zbpm zbpm) {
        BpmNodeHist startNode = new BpmNodeHist();
        startNode.setFacno("N1");
        startNode.setFacna("开始节点");
        startNode.setFacty("start");
        startNode.setProid(zbpm.getProid());
        startNode.setState("30");
        startNode.setEntim(new Date());
        startNode.setTarno("N2");
        startNode.setTarna("起草节点");
        startNode.setId(XstringUtil.getUUID());
        repo.save(startNode);
    }

    public void saveDraftNode(Zbpm zbpm, Znode znode) {
        BpmNodeHist draftNode = new BpmNodeHist();
        draftNode.setFacno("N2");
        draftNode.setFacna("起草节点");
        draftNode.setFacty("draft");
        draftNode.setProid(zbpm.getProid());
        draftNode.setState("30");
        draftNode.setId(znode.getNodid());
        draftNode.setEntim(new Date());
        draftNode.setTarno(znode.getTarno());
        draftNode.setTarna(znode.getTarna());
        repo.save(draftNode);
    }

    public String saveEndNode(Zbpm zbpm) {
        BpmNodeHist endNode = new BpmNodeHist();
        endNode.setFacno("N3");
        endNode.setFacna("结束节点");
        endNode.setFacty("end");
        endNode.setProid(zbpm.getProid());
        endNode.setState("30");
        String uuid = XstringUtil.getUUID();
        endNode.setId(uuid);
        endNode.setEntim(new Date());
        repo.save(endNode);
        return uuid;
    }

    public void saveNodeList(Zbpm zbpm, List<Znode> list) {
        for (Znode znode : list) {
            BpmNodeHist node = new BpmNodeHist();
            node.setFacno(znode.getFacno());
            node.setFacna(znode.getFacna());
            node.setFacty(znode.getFacty());
            node.setProid(zbpm.getProid());
            node.setTarno(znode.getTarno());
            node.setTarna(znode.getTarna());
            node.setState("30");
            node.setId(XstringUtil.getUUID());
            node.setEntim(new Date());
            repo.save(node);
        }
    }

    public BpmNodeHist saveNode(BpmNodeMain main) {
        BpmNodeHist node = new BpmNodeHist();
        node.setId(main.getId());
        node.setFacno(main.getFacno());
        node.setFacna(main.getFacna());
        node.setFacty(main.getFacty());
        node.setProid(main.getProid());
        node.setState("20");
        return repo.save(node);
    }

    @Transactional(readOnly = true)
    public BpmNodeHist findOne(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    @Autowired
    private BpmNodeHistRepo repo;
}
