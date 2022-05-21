package vboot.module.bpm.proc.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XuserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("bpm/proc/main")
public class BpmProcMainApi {

    @GetMapping
    public RestResult get(String name, String catid) {
        Sqler sqler = new Sqler("bpm_proc_main");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        BpmProcMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("zbpm/{proid}")
    public RestResult zbpm(@PathVariable String proid) {
        Map<String, Object> map = new HashMap<>();
        //审批记录
        String sql = "select t.id,t.crtim,t.facna,t.facno,t.opnot,t.opinf,o.name as haman from bpm_audit_main t " +
                "inner join sys_org o on o.id=t.haman " +
                "where t.proid=? order by t.crtim";
        List<Map<String, Object>> audits = jdbcDao.findMapList(sql, proid);
        map.put("audits", audits);

        //历史处理人
        String hiHamen = "";
        for (Map<String, Object> audit : audits) {
            if (!hiHamen.contains("" + audit.get("haman"))) {
                hiHamen += audit.get("haman") + ";";
            }
        }
        if (hiHamen.contains(";")) {
            hiHamen = hiHamen.substring(0, hiHamen.length() - 1);
        }
        map.put("hiHamen", hiHamen);

        //当前处理人与当前用户是否在当前处理人中
        String sql2 = "select n.id as tasid,t.id as nodid,o.name exnam,n.exman,t.proid,t.facno,t.facna from bpm_node_main t" +
                " inner join bpm_task_main n on n.nodid=t.id " +
                "inner join sys_org o on o.id=n.exman " +
                "where t.proid=? and n.actag=1 order by n.ornum";
        List<Map<String, Object>> tasks = jdbcDao.findMapList(sql2, proid);
        String cuExmen = "";
        boolean cutag = false;
        String userid = XuserUtil.getUserId();
        Zbpm zbpm = new Zbpm();
        for (Map<String, Object> task : tasks) {
            if (XstringUtil.isBlank(zbpm.getProid())) {
                zbpm.setProid("" + task.get("proid"));
                zbpm.setNodid("" + task.get("nodid"));
                zbpm.setFacno("" + task.get("facno"));
                zbpm.setFacna("" + task.get("facna"));
            }
            cuExmen += task.get("exnam") + ";";
            if (userid.equals("" + task.get("exman"))) {
                zbpm.setTasid("" + task.get("tasid"));
                zbpm.setExman("" + task.get("exman"));
                cutag = true;
            }
        }
        if (cuExmen.contains(";")) {
            cuExmen = cuExmen.substring(0, cuExmen.length() - 1);
        }
        map.put("cuExmen", cuExmen);
        map.put("cutag", cutag);
        map.put("zbpm", zbpm);
        return RestResult.ok(map);
    }

    @GetMapping("target")
    public RestResult target(String proid, String facno) {
        Map<String, Object> map = new HashMap<>();
        Znode nextNode;
        //如果是之前被驳回的节点则，通过后要判断是否直接返回驳回的节点
        String refuseSql = "select t.id,t.paval from bpm_proc_param t where t.proid=? and t.pakey=?";
        Map<String, Object> bacMap = jdbcDao.findMap(refuseSql, proid, facno + "#refuse");

        String xmlSql = "select t.chxml from bpm_proc_temp t " +
                "inner join bpm_proc_main m on m.temid=t.id  where m.id=?";
        String chxml = jdbcDao.findOneString(xmlSql, proid);
        if (bacMap != null && XstringUtil.isNotBlank("" + bacMap.get("paval"))) {
            nextNode = hand.getNodeInfo(chxml, "" + bacMap.get("paval"));
        } else {
            nextNode = hand.calcTarget(chxml, facno);
        }
        String sql = "select t.name as id from sys_org t where t.id=?";
        String tamen = jdbcDao.findOneString(sql, nextNode.getExman());
        map.put("tarno", nextNode.getFacno());
        map.put("tarna", nextNode.getFacna());
        map.put("tamen", tamen);
        if (bacMap != null) {
            map.put("bacid", bacMap.get("id"));
        }
        return RestResult.ok(map);
    }


    //返回当前节点之前的已走过的节点
    @GetMapping("refnodes")
    public RestResult refNodes(String proid, String facno) {
        String sql = "select distinct t.facno id,t.facna name,t.haman exman from bpm_audit_main t " +
                "where proid=? and opkey in('dsubmit','pass') order by t.crtim";
        List<Map<String, Object>> mapList = jdbcDao.findMapList(sql, proid);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            if (facno.equals(map.get("id"))) {
                break;
            }
            list.add(map);
        }
        return RestResult.ok(list);
    }

    @GetMapping("xml/{proid}")
    public RestResult xml(@PathVariable String proid) {
        Map<String, Object> map = new HashMap<>();
        String xmlSql = "select t.orxml id from bpm_proc_temp t " +
                "inner join bpm_proc_main m on m.temid=t.id where m.id=?";
        String orxml = jdbcDao.findOneString(xmlSql, proid);

        String sql = "select distinct t.facno id from bpm_node_hist t where proid=? order by sttim";
        List<String> nodeList = jdbcDao.findStringList(sql, proid);

        List<Zinp> allLineList = hand.GetAllLineList(orxml);
        HashSet<String> lineSet = new HashSet<>();
        for (Zinp zinp : allLineList) {
            for (String node : nodeList) {
                if (node.equals(zinp.getName())) {
                    for (String node2 : nodeList) {
                        if (node2.equals(zinp.getPid())) {
                            lineSet.add(zinp.getId());
                            break;
                        }
                    }
                    break;
                }
            }
        }

        map.put("xml", orxml);
        map.put("nodeList", nodeList);
        map.put("lineList", lineSet);
        return RestResult.ok(map);
    }

    @GetMapping("texml/{temid}")
    public RestResult texml(@PathVariable String temid) {
        Map<String, Object> map = new HashMap<>();
        String sql = "select t.orxml from bpm_proc_temp t where t.id=?";
        String orxml = jdbcDao.findOneString(sql, temid);
        Znode nextNode = hand.getFirstNode(orxml, "N2");
        String tamenSql = "select t.name as id from sys_org t where t.id=?";
        String tamen = jdbcDao.findOneString(tamenSql, nextNode.getExman());
        map.put("tarno", nextNode.getFacno());
        map.put("tarna", nextNode.getFacna());
        map.put("tamen", tamen);
        map.put("xml", orxml);
        return RestResult.ok(map);
    }

//    @PostMapping("hpass")
//    public RestResult hpass(@RequestBody Zbpm zbpm) {
//
//
//        service.handlerPass(zbpm);
//        return RestResult.ok();
//    }

//    @PostMapping("hrefuse")
//    public RestResult hrefuse(@RequestBody Zbpm zbpm) {
//
//        service.handlerRefuse(zbpm);
//        return RestResult.ok();
//    }

    @PostMapping
    public RestResult post(@RequestBody BpmProcMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody BpmProcMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;


    @Autowired
    private BpmProcMainHand hand;

    @Autowired
    private BpmProcMainService service;

}
