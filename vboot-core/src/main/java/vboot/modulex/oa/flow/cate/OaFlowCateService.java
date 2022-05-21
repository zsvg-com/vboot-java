package vboot.modulex.oa.flow.cate;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseCateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OaFlowCateService extends BaseCateService<OaFlowCate> {


    public List<OaFlowCate> findTree(Sqler sqler) {
        List<OaFlowCate> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(OaFlowCate.class));
        return buildByRecursive(list);
    }

    //使用递归方法建树
    private List<OaFlowCate> buildByRecursive(List<OaFlowCate> nodes) {
        List<OaFlowCate> list = new ArrayList<>();
        for (OaFlowCate node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (OaFlowCate node2 : nodes) {
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
    private OaFlowCate findChildrenByTier(OaFlowCate node, List<OaFlowCate> nodes) {
        for (OaFlowCate item : nodes) {
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
    private OaFlowCateRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }


}
