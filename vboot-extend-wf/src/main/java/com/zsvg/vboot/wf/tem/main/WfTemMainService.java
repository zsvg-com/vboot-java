package com.zsvg.vboot.wf.tem.main;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class WfTemMainService extends BaseMainService<WfTemMain> {



    public List<WfTemMainVo> findTree(String name) {
        Sqler cateSqler = new Sqler("wf_tem_cate");
        cateSqler.addSelect("t.pid,'cate' as type");
        cateSqler.addLike("t.name", name);
        cateSqler.addOrder("t.ornum");
        List<WfTemMainVo> list = jdbcDao.getTp().query(cateSqler.getSql(), cateSqler.getParams(), new BeanPropertyRowMapper<>(WfTemMainVo.class));

        Sqler mainSqler = new Sqler("wf_tem_main");
        mainSqler.addSelect("t.cateid as pid,'main' as type");
        mainSqler.addLike("t.name", name);
        mainSqler.addOrder("t.ornum");
        List<WfTemMainVo> list2 = jdbcDao.getTp().query(mainSqler.getSql(), mainSqler.getParams(), new BeanPropertyRowMapper<>(WfTemMainVo.class));
        list.addAll(list2);
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<WfTemMainVo> buildByRecursive(List<WfTemMainVo> nodes) {
        List<WfTemMainVo> list = new ArrayList<>();
        for (WfTemMainVo node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (WfTemMainVo node2 : nodes) {
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
    private WfTemMainVo findChildrenByTier(WfTemMainVo node, List<WfTemMainVo> nodes) {
        for (WfTemMainVo item : nodes) {
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
    private WfTemMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}

