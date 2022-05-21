package vboot.module.bpm.task.main;


import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.pojo.Zinp;
import vboot.module.bpm.proc.main.Zbpm;
import vboot.module.bpm.proc.main.Znode;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmTaskMainService {

    public BpmTaskMain createTask(Zbpm zbpm, Znode znode) {
        BpmTaskMain task = new BpmTaskMain();
        task.setId(XstringUtil.getUUID());
        task.setProid(zbpm.getProid());
        task.setState("20");
        task.setExman(znode.getExman());
        task.setOrnum(0);
        task.setActag(true);
        task.setNodid(znode.getNodid());
        task.setType("1");
        return repo.save(task);
    }

    @Transactional(readOnly = true)
    public BpmTaskMain findOne(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }


    public void findCurrentExmen(List<Map<String, Object>> itemList) {
        String ids = "(";
        for (Map<String, Object> map : itemList) {
            if (!"30".equals(map.get("state"))) {
                ids += "'" + map.get("id") + "',";
            }
        }
        if (!"(".equals(ids)) {
            ids = ids.substring(0, ids.length() - 1) + ")";
            String sql =
                    "select n.id as tasid,t.id as nodid,o.name exnam,n.exman,t.proid,t.facno,t.facna from bpm_node_main t" +
                            " inner join bpm_task_main n on n.nodid=t.id " +
                            "inner join sys_org o on o.id=n.exman " +
                            "where t.proid in " + ids + " and n.actag=1 order by t.proid, n.ornum";
            List<Map<String, Object>> tasks = jdbcDao.findMapList(sql);

            List<Zinp> list = new ArrayList<>();
            String proid = "";
            for (Map<String, Object> task : tasks) {
                if (!proid.equals(task.get("proid"))) {
                    Zinp zinp = new Zinp();
                    zinp.setId(task.get("proid") + "");
                    zinp.setName(task.get("facno") + "." + task.get("facna"));
                    zinp.setPid("" + task.get("exnam"));
                    list.add(zinp);
                } else {
                    list.get(list.size() - 1).setPid(list.get(list.size() - 1).getPid() + ";" + task.get("exnam"));
                }
                proid = "" + task.get("proid");
            }

            for (Map<String, Object> map : itemList) {
                for (Zinp zinp : list) {
                    if (zinp.getId().equals(map.get("id"))) {
                        map.put("facno", zinp.getName());
                        map.put("exmen", zinp.getPid());
                        break;
                    }
                }
            }
        }
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private BpmTaskMainRepo repo;
}
