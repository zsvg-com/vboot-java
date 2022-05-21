package vboot.module.sys.perm.menu;

import cn.hutool.core.util.StrUtil;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermMenuService {

    @Transactional(readOnly = true)
    public SysPermMenu findById(String id) {

        return repo.findById(id).get();
    }

    public SysPermMenu insert(SysPermMenu main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        return repo.save(main);
    }


    public SysPermMenu update(SysPermMenu main) {
        return repo.save(main);
    }

    public int delete(String[] ids) {
        Set<String> menuSet = new HashSet<>();
        for (String id : ids) {
            menuSet.add(id);
            List<SysPermMenu> menuList = repo.findByPid(id);
            addChild(menuList, menuSet);
        }
        for (String str : menuSet) {
            repo.deleteById(str);
        }
        return menuSet.size();
    }

    public void addChild(List<SysPermMenu> menuList, Set<String> menuSet) {
        for (SysPermMenu menu : menuList) {
            menuSet.add(menu.getId());
            List<SysPermMenu> menus = repo.findByPid(menu.getId());
            if (menus != null && menus.size() != 0) {
                addChild(menus, menuSet);
            }
        }
    }

    public List<SysPermMenu> findTree(Sqler sqler) {
        List<SysPermMenu> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(SysPermMenu.class));
        return buildByRecursive(list);
    }


    public List<SysPermMenu> findAll(String name) {
        List<SysPermMenu> list;
        if (StrUtil.isNotBlank(name)) {
            list = repo.findByNameLikeOrderByOrnum("%" + name + "%");
        } else {
            list = repo.findByOrderByOrnum();
        }
        return buildByRecursive(list);
    }


    public List<SysPermMenu> findWithoutItself(String id) {
        List<SysPermMenu> list = repo.findByOrderByOrnum();
        List<SysPermMenu> menus = buildByRecursiveWithoutItself(list, id);

        return menus;
    }


    //高性能转换，但是要求顶层有pid,这里假设为0
    public static List<SysPermMenu> buildTree(List<SysPermMenu> pidList) {
        Map<String, List<SysPermMenu>> pidListMap = pidList.stream().collect(Collectors.groupingBy(SysPermMenu::getPid));
        pidList.stream().forEach(item -> item.setChildren(pidListMap.get(item.getId())));
        //返回结果也改为返回顶层节点的list
        return pidListMap.get("0");
    }

//    private  List<SysPermMenu> getTreeData(List<SysPermMenu> list) {
//        List<SysPermMenu> tree = new LinkedList<>();
//        if(null != list && !list.isEmpty()){
//            for(SysPermMenu nodeVo: list){
//                addToTreeNodeList(list, nodeVo);
//            }
//
//            for (SysPermMenu nodeVo: list){
//                if(null == nodeVo.getPid()){
//                    tree.add(nodeVo);
//                }
//            }
//        }
//        return tree;
//    }
//
//    private void addToTreeNodeList(final List<SysPermMenu> nodeVoList, SysPermMenu treeNodeVo){
//        if(null != nodeVoList && !nodeVoList.isEmpty()){
//            for(SysPermMenu parentNodeVo: nodeVoList){
//                if(parentNodeVo.getId().equals(treeNodeVo.getPid())){
//                    List<SysPermMenu> children = parentNodeVo.getChildren();
//                    boolean flag = true;
//                    for(SysPermMenu node: children){
//                        if(treeNodeVo.getId().equals(node.getId())){
//                            flag = false;
//                        }
//                    }
//                    if(flag){
//                        children.add(treeNodeVo);
//                    }
//                    break;
//                }else{
//                    addToTreeNodeList(parentNodeVo.getChildren(), treeNodeVo);
//                }
//            }
//        }
//    }


    //使用递归方法建树
    private List<SysPermMenu> buildByRecursive(List<SysPermMenu> nodes) {
        List<SysPermMenu> list = new ArrayList<>();
        for (SysPermMenu node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (SysPermMenu node2 : nodes) {
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
    private SysPermMenu findChildrenByTier(SysPermMenu node, List<SysPermMenu> nodes) {
        for (SysPermMenu item : nodes) {
            if (node.getId().equals(item.getPid())) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<>());
                }
                node.getChildren().add(findChildrenByTier(item, nodes));
            }
        }
        return node;
    }


    //使用递归方法建树不包含自己
    private List<SysPermMenu> buildByRecursiveWithoutItself(List<SysPermMenu> nodes, String id) {
        List<SysPermMenu> list = new ArrayList<>();
        for (SysPermMenu node : nodes) {
            if (node.getPid() == null && !node.getId().equals(id)) {
                list.add(findChildrenByTierWithoutItself(node, nodes, id));
            } else {
                boolean flag = false;
                for (SysPermMenu node2 : nodes) {
                    if (node.getPid() != null&&node.getPid().equals(node2.getId())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag && !node.getId().equals(id)) {
                    list.add(findChildrenByTierWithoutItself(node, nodes, id));
                }
            }
        }
        return list;
    }

    //递归查找子节点不包含自己
    private SysPermMenu findChildrenByTierWithoutItself(SysPermMenu node, List<SysPermMenu> nodes, String id) {
        for (SysPermMenu item : nodes) {
            if (node.getId().equals(item.getPid()) && (!item.getId().equals(id))) {
                if (node.getChildren() == null) {
                    node.setChildren(new ArrayList<>());
                }
                node.getChildren().add(findChildrenByTierWithoutItself(item, nodes, id));
            }
        }
        return node;
    }


    @Autowired
    private SysPermMenuRepo repo;

    @Autowired
    private JdbcDao jdbcDao;
}
