package com.zsvg.vboot.config.security.authn;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.config.security.pojo.Duser;
import com.zsvg.vboot.config.security.pojo.Zmenu;
import com.zsvg.vboot.config.security.pojo.Zmeta;
import com.zsvg.vboot.config.security.pojo.Zuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthnService {

    public void initZuser(Zuser zuser, Duser duser, Map<String, Object> backMap) {
        if (!duser.getRetag()) {
            //1.获取用户所有的组织架构集:conds
            String conds = findConds(duser);
            zuser.setConds(conds);

            //2.根据组织架构集conds查询前台菜单集:menus
            List<Zmenu> menuList = findMenuList(zuser.getConds());

            //3.根据组织架构集conds查询前台按钮集:btns
            List<String> btnList = findBtnList(zuser.getConds());

            //4.计算后台缓存的url权限集
            List<String> permList = new ArrayList<>();
            for (Zmenu menu : menuList) {
                if (StrUtil.isNotBlank(menu.getPerm())) {
                    permList.add(menu.getPerm());
                }
            }
            permList.addAll(btnList);
            zuser.setPermList(permList);

            //5.设置前台返回数据
            menuList = buildTree(menuList);
            backMap.put("menus", menuList);
            backMap.put("btns", btnList);
            backMap.put("zuser", zuser);

            //6.更新用户，序列化保存数据，使下次这些数据可直接从数据库取
            updateUser(zuser, menuList, btnList);
            System.out.println("通过数据库");

        } else {
            String sql = "select conds,menus,btns,perms from sys_org_user where id=?";
            Map<String, Object> map = jdbcDao.findMap(sql, zuser.getId());

            zuser.setConds((String) map.get("conds"));

            List<Zmenu> menuList = JSON.parseArray((String) map.get("menus"), Zmenu.class);

            String[] btnArr = null;
            String btns = (String) map.get("btns");
            if (btns != null) {
                btnArr = btns.split(";");
            }

            String[] permArr = null;
            String perms = (String) map.get("perms");
            if (perms != null) {
                permArr = perms.split(";");
            }
            List<String> permList = new ArrayList<>();
            if (permArr != null) {
                permList = Arrays.asList(permArr);
            }

            zuser.setPermList(permList);
            backMap.put("menus", menuList);
            backMap.put("btns", btnArr);
            backMap.put("zuser", zuser);
            System.out.println("通过缓存");
        }

//            //初始化数据库user
//            String perms = "";
//            for (int i = 0; i < permSum.length; i++) {
//                perms += permSum[i] + ";";
//            }
//            if (!perms.equals("")) {
//                perms = perms.substring(0, perms.length() - 1);
//            } else {
//                perms = "0";
//            }
//            updatePerson(perms, conds, id);
//            zuser = new Zuser(id, "" + orgInfo.get("name"), sysOrgUser.getUsnam(), permSum, conds);
//            zuser.setAdmin(XuserUtil.isAdmin(sysOrgUser.getUsnam()));
//            zuser.setWatag(sysOrgUser.getWatag());
//        } else {
//            String[] permArr = sysOrgUser.getPerms().split(";");
//            long[] permSum = new long[permArr.length];
//            for (int i = 0; i < permArr.length; i++) {
//                permSum[i] = Long.parseLong(permArr[i]);
//            }
//            zuser = new Zuser(id, "" + orgInfo.get("name"), "" + sysOrgUser.getUsnam(), permSum, sysOrgUser.getConds());
//            zuser.setAdmin(XuserUtil.isAdmin(sysOrgUser.getUsnam()));
//            zuser.setWatag(sysOrgUser.getWatag());
//        }
//        return zuser;


    }

//    private void calcPerms(Zuser zuser,){
//        //查询权限集合
//            Integer posSum = SysAuthPermService.AUTHPOS + 1;//取出最大权限位
//            long[] permSum = new long[posSum];
////
//            List<Zperm> zpermList = getPermList(conds);
//            for (Zperm zperm : zpermList) {
//                for (int i = 0; i < posSum; i++) {
//                    if (i == zperm.getPos()) {
//                        permSum[i] = permSum[i] + zperm.getCode();
//                    }
//                }
//            }
//    }


    private String findConds(Duser duser) {
        StringBuilder conds = new StringBuilder();
        //1. conds拼接父级id
        if (StrUtil.isNotBlank(duser.getTier())) {
            String[] pidArr = duser.getTier().split("x");
            for (int i = pidArr.length - 1; i >= 0; i--) {
                if (!"".equals(pidArr[i])) {
                    conds.append("'").append(pidArr[i]).append("',");
                }
            }
        } else {
            conds = new StringBuilder("'" + duser.getId() + "',");
        }
        //2. conds拼接岗位id
        List<String> postList = findPostList(duser.getId());
        for (String str : postList) {
            conds.append("'").append(str).append("',");
        }
        conds = new StringBuilder(conds.substring(0, conds.length() - 1));//优化
        //3. conds拼接群组id
        List<String> groupList = findGroupList(conds.toString());
        for (String str : groupList) {
            conds.append(",'").append(str).append("'");
        }
        return conds.toString();
    }


    private List<String> findPostList(String uid) {
        String sql = "select pid as id from sys_org_post_user where uid=?";
        return jdbcDao.findStringList(sql, uid);
    }

    private List<String> findGroupList(String conds) {
        String sql = "select DISTINCT gid as id from sys_org_group_org where oid in (" + conds + ")";
        return jdbcDao.findStringList(sql);
    }


    private List<Zmenu> findMenuList(String conds) {
        String sql = "select distinct m.name,m.code,m.path,m.icon,m.comp,m.ornum,m.id,m.pid,m.perm from sys_auth_menu m inner join sys_auth_role_menu rm on rm.mid=m.id inner join sys_auth_role_org ru on ru.rid=rm.rid where m.type in ('D','M') and m.avtag=1 and ru.oid in (" + conds + ") order by m.ornum";
        if(conds.contains("'sa'")||conds.contains("'vben'")){
            sql = "select m.name,m.code,m.path,m.icon,m.comp,m.ornum,m.id,m.pid,m.perm from sys_auth_menu m where m.type in ('D','M') and m.avtag=1 order by m.ornum";
        }

        List<Zmenu> list = jdbcDao.getTp().query(sql, (rs, rowNum) -> {
            Zmenu zmenu = new Zmenu();
            zmenu.setId(rs.getString("id"));
            zmenu.setPid(rs.getString("pid"));
            zmenu.setPerm(rs.getString("perm"));
            zmenu.setName(rs.getString("code"));
            zmenu.setPath(rs.getString("path"));
            zmenu.setComponent(rs.getString("comp"));
            Zmeta zmeta = new Zmeta();
            zmeta.setTitle(rs.getString("name"));
            zmeta.setOrderNo(rs.getInt("ornum"));
            zmeta.setIcon(rs.getString("icon"));
            zmenu.setMeta(zmeta);
            return zmenu;
        });
        return list;
    }


    //使用递归方法建树
    private List<Zmenu> buildTree(List<Zmenu> treeNodes) {
        List<Zmenu> trees = new ArrayList<>();
        for (Zmenu treeNode : treeNodes) {
            if (treeNode.getPid() == null) {
                trees.add(recTree(treeNode, treeNodes));
            }
        }
        return trees;
    }


    //递归查找子节点
    private Zmenu recTree(Zmenu treeNode, List<Zmenu> treeNodes) {
        for (Zmenu it : treeNodes) {
            if (treeNode.getId().equals(it.getPid())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(recTree(it, treeNodes));
            }
        }
        return treeNode;
    }


    private List<String> findBtnList(String conds) {
        String sql = "select distinct m.perm id from sys_auth_menu m inner join sys_auth_role_menu rm on rm.mid=m.id inner join sys_auth_role_org ru on ru.rid=rm.rid where m.type = 'B' and m.avtag=1 and ru.oid in (" + conds + ") order by m.ornum";
        if(conds.contains("'sa'")||conds.contains("'vben'")){
            sql = "select m.perm id from sys_auth_menu m where m.type = 'B' and m.avtag=1 order by m.ornum";
        }
        return jdbcDao.findStringList(sql);
    }


    private void updateUser(Zuser zuser, List<Zmenu> menuList, List<String> btnList) {

        String menus = JSON.toJSONString(menuList);
        StringBuilder btns = new StringBuilder();
        for (String str : btnList) {
            btns.append(str).append(";");
        }

        StringBuilder perms = new StringBuilder();
        for (String str : zuser.getPermList()) {
            perms.append(str).append(";");
        }

        String sql = "update sys_org_user set conds=?,menus=?,btns=?,perms=?,retag=1 where id=?";
        jdbcDao.update(sql, zuser.getConds(), menus, btns.toString(), perms.toString(), zuser.getId());
    }


    @Transactional(readOnly = true)
    public Zuser getUser(String username) {
        Map<String, Object> map = jdbcDao.getTp().queryForMap("select u.id,u.name from sys_org_user u where u.usnam=? and u.avtag=1", username);
        Zuser user = new Zuser();
        user.setId((String) map.get("id"));
        user.setName((String) map.get("name"));
        return user;
    }


    @Autowired
    private JdbcDao jdbcDao;


}
