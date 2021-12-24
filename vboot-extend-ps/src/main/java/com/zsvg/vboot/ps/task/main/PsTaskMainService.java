package com.zsvg.vboot.ps.task.main;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.service.BaseMainService;
import com.zsvg.vboot.ps.proj.cate.PsProjCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PsTaskMainService extends BaseMainService<PsTaskMain> {

    public List<PsTaskMain> findTree(Sqler sqler) {
        List<PsTaskMain> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(PsTaskMain.class));
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<PsTaskMain> buildByRecursive(List<PsTaskMain> nodes) {
        List<PsTaskMain> list = new ArrayList<>();
        for (PsTaskMain node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (PsTaskMain node2 : nodes) {
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
    private PsTaskMain findChildrenByTier(PsTaskMain node, List<PsTaskMain> nodes) {
        for (PsTaskMain item : nodes) {
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
    private PsTaskMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}


