package vboot.modulex.oa.flow.temp;

import vboot.common.mvc.pojo.Zinp;
import vboot.common.mvc.service.BaseMainService;
import vboot.module.bpm.proc.temp.BpmProcTemp;
import vboot.module.bpm.proc.temp.BpmProcTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OaFlowTempService extends BaseMainService<OaFlowTemp> {

    public OaFlowTemp insertx(OaFlowTemp oaFlowTemp) {
        BpmProcTemp bpmProcTemp = new BpmProcTemp(oaFlowTemp);
        bpmProcTemp.setOrxml(oaFlowTemp.getPrxml());
        String chxml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"
                + "\n<process" + bpmProcTemp.getOrxml().split("bpmn2:process")[1]
                .replaceAll("bpmn2:", "")
                .replaceAll("activiti:", "") + "process>";
        bpmProcTemp.setChxml(chxml);
        BpmProcTemp dbProcTemp = bpmProcTempService.insert(bpmProcTemp);
        oaFlowTemp.setProtd(dbProcTemp.getId());
        return super.insert(oaFlowTemp);
    }

    public OaFlowTemp updatex(OaFlowTemp oaFlowTemp) {
        BpmProcTemp bpmProcTemp = bpmProcTempService.findOne(oaFlowTemp.getProtd());
        bpmProcTemp.setOrxml(oaFlowTemp.getPrxml());
        String chxml = "<?xml version=\"1.0\" encoding=\"gb2312\"?>"
                + "\n<process" + bpmProcTemp.getOrxml().split("bpmn2:process")[1]
                .replaceAll("bpmn2:", "")
                .replaceAll("activiti:", "") + "process>";
        bpmProcTemp.setChxml(chxml);
        bpmProcTempService.update(bpmProcTemp);
        return super.update(oaFlowTemp);
    }

    public List<Zinp> findTreeList() {
        String sql1 = "select id,name,pid from oa_flow_cate";
        List<Zinp> list1 = jdbcDao.findInpList(sql1);
        String sql2 = "select id,name,catid pid from oa_flow_temp";
        List<Zinp> list2 = jdbcDao.findInpList(sql2);
        list1.addAll(list2);
        return buildByRecursive(list1);
    }

    //使用递归方法建树
    private List<Zinp> buildByRecursive(List<Zinp> nodes) {
        List<Zinp> list = new ArrayList<>();
        for (Zinp node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (Zinp node2 : nodes) {
                    if (node.getPid().equals(node2.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    list.add(findChildrenByTier(node, nodes));
                }
            }
        }
        return list;
    }


    //递归查找子节点
    private Zinp findChildrenByTier(Zinp node, List<Zinp> nodes) {
        for (Zinp item : nodes) {
            if (node.getId().equals(item.getPid())) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<>());
                }
                node.getChildren().add(findChildrenByTier(item, nodes));
            }
        }
        return node;
    }

    //----------bean注入------------
    @Autowired
    private BpmProcTempService bpmProcTempService;

    @Autowired
    private OaFlowTempRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
