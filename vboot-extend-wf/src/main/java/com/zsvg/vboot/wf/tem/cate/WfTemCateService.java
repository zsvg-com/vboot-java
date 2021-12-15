package com.zsvg.vboot.wf.tem.cate;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class WfTemCateService extends BaseCateService<WfTemCate> {


    public List<WfTemCate> findTree(Sqler sqler) {
        List<WfTemCate> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(WfTemCate.class));
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<WfTemCate> buildByRecursive(List<WfTemCate> nodes) {
        List<WfTemCate> list = new ArrayList<>();
        for (WfTemCate node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (WfTemCate node2 : nodes) {
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
    private WfTemCate findChildrenByTier(WfTemCate node, List<WfTemCate> nodes) {
        for (WfTemCate item : nodes) {
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
    private WfTemCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
