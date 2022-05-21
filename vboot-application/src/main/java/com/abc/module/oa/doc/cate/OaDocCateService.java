package com.abc.module.oa.doc.cate;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OaDocCateService extends BaseCateService<OaDocCate> {


    public List<OaDocCate> findTree(Sqler sqler) {
        List<OaDocCate> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(OaDocCate.class));
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<OaDocCate> buildByRecursive(List<OaDocCate> nodes) {
        List<OaDocCate> list = new ArrayList<>();
        for (OaDocCate node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (OaDocCate node2 : nodes) {
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
    private OaDocCate findChildrenByTier(OaDocCate node, List<OaDocCate> nodes) {
        for (OaDocCate item : nodes) {
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
    private OaDocCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
