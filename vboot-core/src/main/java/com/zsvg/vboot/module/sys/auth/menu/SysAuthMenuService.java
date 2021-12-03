package com.zsvg.vboot.module.sys.auth.menu;

import cn.hutool.core.util.StrUtil;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysAuthMenuService {

    @Transactional(readOnly = true)
    public SysAuthMenu findById(String id) {

        return repo.findById(id).get();
    }

    public SysAuthMenu insert(SysAuthMenu main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        return repo.save(main);
    }


    public SysAuthMenu update(SysAuthMenu main) {
        return repo.save(main);
    }

    public int delete(String[] ids) {
        Set<String> menuSet = new HashSet<>();
        for (String id : ids) {
            menuSet.add(id);
            List<SysAuthMenu> menuList = repo.findByPid(id);
            addChild(menuList, menuSet);
        }
        for (String str : menuSet) {
            repo.deleteById(str);
        }
        return menuSet.size();
    }

    public void addChild(List<SysAuthMenu> menuList, Set<String> menuSet) {
        for (SysAuthMenu menu : menuList) {
            menuSet.add(menu.getId());
            List<SysAuthMenu> menus = repo.findByPid(menu.getId());
            if (menus != null && menus.size() != 0) {
                addChild(menus, menuSet);
            }
        }
    }

    public List<SysAuthMenu> findTree(Sqler sqler) {
        List<SysAuthMenu> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(SysAuthMenu.class));
        return buildByRecursive(list);
    }


    public List<SysAuthMenu> findAll(String name) {
        List<SysAuthMenu> list;
        if (StrUtil.isNotBlank(name)) {
            list = repo.findByNameLikeOrderByOrnum("%" + name + "%");
        } else {
            list = repo.findByOrderByOrnum();
        }
        return buildByRecursive(list);
    }


    public List<SysAuthMenu> findWithoutItself(String id) {
        List<SysAuthMenu> list = repo.findByOrderByOrnum();
        List<SysAuthMenu> menus = buildByRecursiveWithoutItself(list, id);

        return menus;
    }


    //高性能转换，但是要求顶层有pid,这里假设为0
    public static List<SysAuthMenu> buildTree(List<SysAuthMenu> pidList) {
        Map<String, List<SysAuthMenu>> pidListMap = pidList.stream().collect(Collectors.groupingBy(SysAuthMenu::getPid));
        pidList.stream().forEach(item -> item.setChildren(pidListMap.get(item.getId())));
        //返回结果也改为返回顶层节点的list
        return pidListMap.get("0");
    }

//    private  List<SysAuthMenu> getTreeData(List<SysAuthMenu> list) {
//        List<SysAuthMenu> tree = new LinkedList<>();
//        if(null != list && !list.isEmpty()){
//            for(SysAuthMenu nodeVo: list){
//                addToTreeNodeList(list, nodeVo);
//            }
//
//            for (SysAuthMenu nodeVo: list){
//                if(null == nodeVo.getPid()){
//                    tree.add(nodeVo);
//                }
//            }
//        }
//        return tree;
//    }
//
//    private void addToTreeNodeList(final List<SysAuthMenu> nodeVoList, SysAuthMenu treeNodeVo){
//        if(null != nodeVoList && !nodeVoList.isEmpty()){
//            for(SysAuthMenu parentNodeVo: nodeVoList){
//                if(parentNodeVo.getId().equals(treeNodeVo.getPid())){
//                    List<SysAuthMenu> children = parentNodeVo.getChildren();
//                    boolean flag = true;
//                    for(SysAuthMenu node: children){
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
    private List<SysAuthMenu> buildByRecursive(List<SysAuthMenu> nodes) {
        List<SysAuthMenu> list = new ArrayList<>();
        for (SysAuthMenu node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (SysAuthMenu node2 : nodes) {
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
    private SysAuthMenu findChildrenByTier(SysAuthMenu node, List<SysAuthMenu> nodes) {
        for (SysAuthMenu item : nodes) {
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
    private List<SysAuthMenu> buildByRecursiveWithoutItself(List<SysAuthMenu> nodes, String id) {
        List<SysAuthMenu> list = new ArrayList<>();
        for (SysAuthMenu node : nodes) {
            if (node.getPid() == null && !node.getId().equals(id)) {
                list.add(findChildrenByTierWithoutItself(node, nodes, id));
            } else {
                boolean flag = false;
                for (SysAuthMenu node2 : nodes) {
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
    private SysAuthMenu findChildrenByTierWithoutItself(SysAuthMenu node, List<SysAuthMenu> nodes, String id) {
        for (SysAuthMenu item : nodes) {
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
    private SysAuthMenuRepo repo;

    @Autowired
    private JdbcDao jdbcDao;
}
