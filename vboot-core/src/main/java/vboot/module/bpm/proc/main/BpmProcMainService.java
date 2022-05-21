package vboot.module.bpm.proc.main;

import vboot.common.mvc.service.BaseMainService;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XuserUtil;
import vboot.module.bpm.audit.main.BpmAuditMainService;
import vboot.module.bpm.node.hist.BpmNodeHist;
import vboot.module.bpm.node.hist.BpmNodeHistService;
import vboot.module.bpm.node.main.BpmNodeMain;
import vboot.module.bpm.node.main.BpmNodeMainService;
import vboot.module.bpm.proc.param.BpmProcParam;
import vboot.module.bpm.proc.param.BpmProcParamService;
import vboot.module.bpm.task.hist.BpmTaskHist;
import vboot.module.bpm.task.hist.BpmTaskHistService;
import vboot.module.bpm.task.main.BpmTaskMain;
import vboot.module.bpm.task.main.BpmTaskMainService;
import vboot.module.sys.todo.main.SysTodoMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BpmProcMainService extends BaseMainService<BpmProcMain> {

    public Znode start(Zbpm zbpm) {
        //1 保存流程实例
        BpmProcMain bpmProcMain = new BpmProcMain(zbpm);
        super.insert(bpmProcMain);

        //2 历史节点表保存开始节点
        nodeHistService.saveStartNode(zbpm);

        //3 流程流转（收集流转过的节点，计算出下一个待审批节点）
        Znode draftNode = new Znode("N2");
        draftNode.setFacna("起草节点");
        draftNode.setFacty("draft");
        List<Znode> list = new ArrayList<>();

        String xmlSql = "select t.chxml from bpm_proc_temp t where t.id=?";
        String chxml = jdbcDao.findOneString(xmlSql,zbpm.getTemid());
        zbpm.setChxml(chxml);
        Znode nextNode = hand.procFlow(zbpm, list, draftNode);//流转核心逻辑

        //4.1 历史节点表保存起草节点
        draftNode.setNodid(XstringUtil.getUUID());
        nodeHistService.saveDraftNode(zbpm, draftNode);
        //4.2 评审表保存起草节点的评审信息
        auditMainService.saveDraftAudit(zbpm, draftNode);
        //4.3 历史节点表保存其他已流节点（条件分支等非审批节点）
        nodeHistService.saveNodeList(zbpm, list);
        //4.4 当前节点表保存下一个待审批节点
        BpmNodeMain nodeMain = nodeMainService.saveNode(zbpm, nextNode);
        nextNode.setNodid(nodeMain.getId());
        //4.5 历史节点表保存下一个待审批节点
        nodeHistService.saveNode(nodeMain);

        //5.1 当前任务表创建待审节点的任务
        BpmTaskMain mainTask = taskMainService.createTask(zbpm, nextNode);
        //5.2 历史任务表创建待审节点的任务
        taskHistService.createTask(mainTask);

        //6 发起待办
        todoService.sendTodo(zbpm, nextNode);
        return nextNode;
    }


    public Znode handlerPass(Zbpm zbpm) {
        zbpm.setHaman(XuserUtil.getUserId());

        String sql = "select m.id as proid,m.name as prona from bpm_proc_main m where m.id=?";
        Map<String, Object> map = jdbcDao.findMap(sql, zbpm.getProid());
        zbpm.setProna("" + map.get("prona"));

        zbpm.setOpkey("pass");
        zbpm.setOpinf("通过");

        //1 评审表保存当前节点的评审信息
        auditMainService.saveAudit(zbpm);
        if(XstringUtil.isNotBlank(zbpm.getBacid())){
            paramService.delete(zbpm.getBacid());
        }

        //2.1 将历史任务变成已办
        BpmTaskHist histTask = taskHistService.findOne(zbpm.getTasid());
        histTask.setHaman(zbpm.getHaman());
        histTask.setEntim(new Date());
        histTask.setState("30");
        //2.2 删除当前任务表记录
        taskMainService.delete(zbpm.getTasid());

        //3 流程流转
        Znode currNode = new Znode(zbpm.getFacno());
        currNode.setNodid(zbpm.getNodid());
        currNode.setFacno(zbpm.getFacno());
        currNode.setFacty("review");
        if(XstringUtil.isNotBlank(zbpm.getBacid())){
            currNode.setTarno(zbpm.getTarno());
            currNode.setTarna(zbpm.getTarna());
        }
        List<Znode> list = new ArrayList<>();
        String xmlSql = "select t.chxml from bpm_proc_temp t " +
                "inner join bpm_proc_main m on m.temid=t.id  where m.id=?";
        String chxml =   jdbcDao.findOneString(xmlSql, zbpm.getProid());;
        zbpm.setChxml(chxml);
        Znode nextNode = hand.procFlow(zbpm, list, currNode);//流转核心逻辑

        //4.1 将历史节点变成已办
        BpmNodeHist histNode = nodeHistService.findOne(zbpm.getNodid());
        histNode.setEntim(new Date());
        histNode.setState("30");
        histNode.setTarno(currNode.getTarno());
        histNode.setTarna(currNode.getTarna());
        currNode.setFacna(histNode.getFacna());
        //4.2 历史节点表保存已流节点
        nodeHistService.saveNodeList(zbpm, list);
        //4.3 删除当前节点表记录
        nodeMainService.delete(zbpm.getNodid());

        if (!"end".equals(nextNode.getFacty())) {
            //5.1 当前节点表保存下一个待审批节点
            BpmNodeMain nodeMain = nodeMainService.saveNode(zbpm, nextNode);
            nextNode.setNodid(nodeMain.getId());
            //5.2 历史节点表保存下一个待审批节点
            nodeHistService.saveNode(nodeMain);

            //6.1 当前任务表创建待审节点的任务
            BpmTaskMain mainTask = taskMainService.createTask(zbpm, nextNode);
            //6.2 历史任务表创建待审节点的任务
            taskHistService.createTask(mainTask);

            //7.1 删除之前的待办
            todoService.doneTodo(zbpm);
            //7.2 发起新待办
            todoService.sendTodo(zbpm, nextNode);
        } else {
            //5 历史节点表保存结束节点
            String endNodeId=nodeHistService.saveEndNode(zbpm);

            //6 评审表保存结束节点的评审信息
            auditMainService.saveEndAudit(zbpm,endNodeId);

            //7 删除之前的待办
            todoService.doneTodo(zbpm);

            //8 将流程更新成完结
            String procUpdateSql = "update bpm_proc_main set state='30' where id=?";
            jdbcDao.update(procUpdateSql,zbpm.getProid());
        }

        return nextNode;
    }

    public Znode handlerRefuse(Zbpm zbpm) {
        zbpm.setHaman(XuserUtil.getUserId());

        String sql = "select m.id as proid,m.name as prona from bpm_proc_main m where m.id=?";
        Map<String, Object> map = jdbcDao.findMap(sql, zbpm.getProid());
        zbpm.setProna("" + map.get("prona"));

        //驳回: "起草节点"（返回本人）
        if(zbpm.getRetag()){
            zbpm.setOpinf("驳回: "+zbpm.getTarno()+"."+zbpm.getTarna()+"（返回本人）");
        }else{
            zbpm.setOpinf("驳回: "+zbpm.getTarno()+"."+zbpm.getTarna());
        }

        //1 评审表保存当前节点的评审信息
        auditMainService.saveAudit(zbpm);

        //2.1 将历史任务变成已办
        BpmTaskHist histTask = taskHistService.findOne(zbpm.getTasid());
        histTask.setHaman(zbpm.getHaman());
        histTask.setEntim(new Date());
        histTask.setState("30");
        //2.2 删除当前任务表记录
        taskMainService.delete(zbpm.getTasid());

        //3 创建驳回节点
//        Znode refuseNode = hand.getNodeInfo(zbpm,zbpm.getTarno());
        Znode refuseNode = new Znode();
        refuseNode.setFacno(zbpm.getTarno());
        refuseNode.setFacna(zbpm.getTarna());
        refuseNode.setExman(zbpm.getExman());
        refuseNode.setFacty("review");


        //4.1 将历史节点变成已办
        BpmNodeHist histNode = nodeHistService.findOne(zbpm.getNodid());
        histNode.setTarno(zbpm.getTarno());
        histNode.setTarna(zbpm.getTarna());
        histNode.setEntim(new Date());
        histNode.setState("30");
        //4.2 删除当前节点表记录
        nodeMainService.delete(zbpm.getNodid());

        //5.1 当前节点表保存下一个待审批节点
        BpmNodeMain nodeMain = nodeMainService.saveNode(zbpm, refuseNode);
        refuseNode.setNodid(nodeMain.getId());
        //5.2 历史节点表保存下一个待审批节点
        nodeHistService.saveNode(nodeMain);
        //5.3 如果驳回时勾选了 驳回的节点通过后直接返回本节点
        if(zbpm.getRetag()==true){
            BpmProcParam param = new BpmProcParam();
            param.setId(XstringUtil.getUUID());
            param.setProid(zbpm.getProid());
            param.setOffty("proc");
            param.setOffid(zbpm.getProid());
            param.setPakey(zbpm.getTarno()+"#refuse");
            param.setPaval(zbpm.getFacno());
            paramService.save(param);
        }

        //6.1 当前任务表创建待审节点的任务
        BpmTaskMain mainTask = taskMainService.createTask(zbpm, refuseNode);
        //6.2 历史任务表创建待审节点的任务
        taskHistService.createTask(mainTask);

        //7.1 删除之前的待办
        todoService.doneTodo(zbpm);
        //7.2 发起新待办
        todoService.sendTodo(zbpm, refuseNode);
        return refuseNode;
    }

    public Zbpm getZbpm(String proid) {
        String sql = "select t.id as tasid,t.nodid,t.exman,t.proid,n.facno,n.facna from bpm_task_main t" +
                " inner join bpm_node_main n on n.id=t.nodid where t.proid=? and t.actag=1";
        List<Zbpm> list = jdbcDao.getTp().query(sql, new Object[]{proid},
                new BeanPropertyRowMapper<>(Zbpm.class));
        String userid = XuserUtil.getUserId();
        for (Zbpm zbpm : list) {
            if (userid.equals(zbpm.getExman())) {
                return zbpm;
            }
        }
        return null;
    }


    //----------bean注入------------
    @Autowired
    private SysTodoMainService todoService;

    @Autowired
    private BpmTaskHistService taskHistService;

    @Autowired
    private BpmTaskMainService taskMainService;

    @Autowired
    private BpmNodeHistService nodeHistService;

    @Autowired
    private BpmNodeMainService nodeMainService;

    @Autowired
    private BpmAuditMainService auditMainService;

    @Autowired
    private BpmProcParamService paramService;

    @Autowired
    private BpmProcMainHand hand;

    @Autowired
    private BpmProcMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
