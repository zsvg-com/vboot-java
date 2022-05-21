package vboot.config.security.authz;

import vboot.common.mvc.dao.JdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class AuthzService {


//    public List<Zmenu> findMenuList(Authentication auth) {
//        String sql = "select name,code,path,icon,comp,ornum,id,pid from sys_perm_menu where type in ('D','M') and avtag=1 order by ornum";
//        List<Zmenu> list=jdbcDao.getTp().query(sql, (rs, rowNum) -> {
//            Zmenu zmenu = new Zmenu();
//            zmenu.setId(rs.getString("id"));
//            zmenu.setPid(rs.getString("pid"));
//            zmenu.setName(rs.getString("code"));
//            zmenu.setPath(rs.getString("path"));
//            zmenu.setComponent(rs.getString("comp"));
//            Zmeta zmeta = new Zmeta();
//            zmeta.setTitle(rs.getString("name"));
//            zmeta.setOrderNo(rs.getInt("ornum"));
//            zmeta.setIcon(rs.getString("icon"));
//            zmenu.setMeta(zmeta);
//            return zmenu;
//        });
//        return buildByRecursive(list);
//    }
//
//
//    //使用递归方法建树
//    public List<Zmenu> buildByRecursive(List<Zmenu> treeNodes) {
//        List<Zmenu> trees = new ArrayList<>();
//        for (Zmenu treeNode : treeNodes) {
//            if (treeNode.getPid()==null) {
//                trees.add(findChildrenByTier(treeNode,treeNodes));
//            }
//        }
//        return trees;
//    }
//
//
//    //递归查找子节点
//    public Zmenu findChildrenByTier(Zmenu treeNode, List<Zmenu> treeNodes) {
////        treeNode.setChildren(new ArrayList<>());
//        for (Zmenu it : treeNodes) {
//            if(treeNode.getId().equals(it.getPid())) {
//                if (treeNode.getChildren() == null) {
//                    treeNode.setChildren(new ArrayList<>());
//                }
//                treeNode.getChildren().add(findChildrenByTier(it,treeNodes));
//            }
//        }
//        return treeNode;
//    }


    @Autowired
    private JdbcDao jdbcDao;



}
