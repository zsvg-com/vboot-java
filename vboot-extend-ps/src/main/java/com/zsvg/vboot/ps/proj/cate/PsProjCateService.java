package com.zsvg.vboot.ps.proj.cate;

import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class PsProjCateService extends BaseCateService<PsProjCate> {

    public List<PsProjCate> findTree(Sqler sqler) {
        List<PsProjCate> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(PsProjCate.class));
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<PsProjCate> buildByRecursive(List<PsProjCate> nodes) {
        List<PsProjCate> list = new ArrayList<>();
        for (PsProjCate node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (PsProjCate node2 : nodes) {
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
    private PsProjCate findChildrenByTier(PsProjCate node, List<PsProjCate> nodes) {
        for (PsProjCate item : nodes) {
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
    private PsProjCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}
