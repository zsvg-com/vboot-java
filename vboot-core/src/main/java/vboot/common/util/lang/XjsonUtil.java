package vboot.common.util.lang;

import vboot.common.mvc.pojo.Zinp;
import vboot.common.mvc.pojo.Ztree;

import java.util.ArrayList;
import java.util.List;

public class XjsonUtil {


    //使用递归方法建树
    public static List<Ztree> buildByRecursive(List<Ztree> treeNodes) {
        List<Ztree> trees = new ArrayList<>();
        for (Ztree treeNode : treeNodes) {
            if (treeNode.getPid()==null) {
                trees.add(findChildrenByTier(treeNode,treeNodes));
            }
        }
        return trees;
    }




    //递归查找子节点
    public static Ztree findChildrenByTier(Ztree treeNode, List<Ztree> treeNodes) {
//        treeNode.setChildren(new ArrayList<>());
        for (Ztree it : treeNodes) {
            if(treeNode.getId().equals(it.getPid())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildrenByTier(it,treeNodes));
            }
        }
        return treeNode;
    }


    //
    public static List<Ztree> inpToTree(List<Zinp> inpList) {
        List<Ztree> trees = new ArrayList<>();
        for (Zinp zinp : inpList) {
            if (zinp.getPid()==null) {
                trees.add(findChildrenByTree(zinp,inpList));
            }
        }
        return trees;
    }


    public static Ztree findChildrenByTree(Zinp zinp, List<Zinp> zinpList) {
//        treeNode.setChildren(new ArrayList<>());
        Ztree ztree =new Ztree();
        ztree.setId(zinp.getId());
        ztree.setName(zinp.getName());
        for (Zinp it : zinpList) {
            if(zinp.getId().equals(it.getPid())) {
                if (ztree.getChildren() == null) {
                    ztree.setChildren(new ArrayList<>());
                }
                ztree.getChildren().add(findChildrenByTree(it,zinpList));
            }
        }
        return ztree;
    }


}
