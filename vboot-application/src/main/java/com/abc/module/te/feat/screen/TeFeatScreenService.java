package com.abc.module.te.feat.screen;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeFeatScreenService extends BaseMainService<TeFeatScreen> {

    public List<Zinp> findTreeList(String name) {
        Sqler cateSqler = new Sqler("te_prod_cate");
        cateSqler.addSelect("null as pid");
        cateSqler.addOrder("t.ornum");
        List<Zinp> list = jdbcDao.findInpList(cateSqler);

        Sqler mainSqler = new Sqler("te_feat_screen");
        mainSqler.addSelect("t.catid as pid");
        mainSqler.addLike("t.name", name);
        mainSqler.addOrder("t.ornum");
        List<Zinp> list2 = jdbcDao.findInpList(mainSqler);
        list.addAll(list2);

        return buildByRecursive(list);
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
    private TeFeatScreenRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
