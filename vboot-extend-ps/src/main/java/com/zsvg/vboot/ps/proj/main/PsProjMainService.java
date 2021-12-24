package com.zsvg.vboot.ps.proj.main;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PsProjMainService extends BaseMainService<PsProjMain> {

    public List<PsProjMainVo> findTree(String name) {
        Sqler cateSqler = new Sqler("ps_proj_cate");
        cateSqler.addSelect("t.pid,'cate' as type");
        cateSqler.addLike("t.name", name);
        cateSqler.addOrder("t.ornum");
        List<PsProjMainVo> list = jdbcDao.getTp().query(cateSqler.getSql(), cateSqler.getParams(), new BeanPropertyRowMapper<>(PsProjMainVo.class));

        Sqler mainSqler = new Sqler("ps_proj_main");
        mainSqler.addSelect("t.cid as pid,'main' as type");
        mainSqler.addLike("t.name", name);
        mainSqler.addOrder("t.ornum");
        List<PsProjMainVo> list2 = jdbcDao.getTp().query(mainSqler.getSql(), mainSqler.getParams(), new BeanPropertyRowMapper<>(PsProjMainVo.class));
        list.addAll(list2);
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<PsProjMainVo> buildByRecursive(List<PsProjMainVo> nodes) {
        List<PsProjMainVo> list = new ArrayList<>();
        for (PsProjMainVo node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (PsProjMainVo node2 : nodes) {
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
    private PsProjMainVo findChildrenByTier(PsProjMainVo node, List<PsProjMainVo> nodes) {
        for (PsProjMainVo item : nodes) {
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
    private PsProjMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}


