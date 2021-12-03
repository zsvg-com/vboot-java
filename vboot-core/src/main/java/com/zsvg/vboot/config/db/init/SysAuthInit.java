package com.zsvg.vboot.config.db.init;

import com.zsvg.vboot.module.sys.auth.menu.SysAuthMenu;
import com.zsvg.vboot.module.sys.auth.menu.SysAuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysAuthInit {

    protected void initMenu() {
        SysAuthMenu menu1 = new SysAuthMenu();
        menu1.setId("Sys");
        menu1.setName("系统管理");
        menu1.setCode("Sys");
        menu1.setComp("LAYOUT");
        menu1.setPath("/sys");
        menu1.setRedirect("/sys/org/user");
        menu1.setOrnum(1);
        menu1.setIcon("ant-design:setting-outlined");
        menu1.setAvtag(true);
        menu1.setShtag(true);
        menu1.setType("D");
        menuService.insert(menu1);

        SysAuthMenu menu11 = new SysAuthMenu();
        menu11.setId("SysOrg");
        menu11.setName("组织架构");
        menu11.setCode("SysOrg");
        menu11.setComp("LAYOUT");
        menu11.setPath("/sys/org");
        menu11.setRedirect("/sys/org/user");
        menu11.setOrnum(1);
        menu11.setIcon("ant-design:partition-outlined");
        menu11.setPid("Sys");
        menu11.setAvtag(true);
        menu11.setShtag(true);
        menu11.setType("D");
        menuService.insert(menu11);

        SysAuthMenu menu111 = new SysAuthMenu();
        menu111.setId("SysOrgDept");
        menu111.setName("部门管理");
        menu111.setCode("SysOrgDept");
        menu111.setPath("/sys/org/dept");
        menu111.setComp("/sys/org/dept/index.vue");
        menu111.setOrnum(1);
        menu111.setPid("SysOrg");
        menu111.setCatag(false);
        menu111.setAvtag(true);
        menu111.setShtag(true);
        menu111.setType("M");
        menuService.insert(menu111);

        SysAuthMenu menu112 = new SysAuthMenu();
        menu112.setId("SysOrgUser");
        menu112.setName("用户管理");
        menu112.setCode("SysOrgUser");
        menu112.setPath("/sys/org/user");
        menu112.setComp("/sys/org/user/index.vue");
        menu112.setOrnum(2);
        menu112.setPid("SysOrg");
        menu112.setCatag(false);
        menu112.setAvtag(true);
        menu112.setShtag(true);
        menu112.setType("M");
        menuService.insert(menu112);

        SysAuthMenu menu113 = new SysAuthMenu();
        menu113.setId("SysOrgPost");
        menu113.setName("岗位管理");
        menu113.setCode("SysOrgPost");
        menu113.setPath("/sys/org/post");
        menu113.setComp("/sys/org/post/index.vue");
        menu113.setOrnum(3);
        menu113.setPid("SysOrg");
        menu113.setCatag(false);
        menu113.setAvtag(true);
        menu113.setShtag(true);
        menu113.setType("M");
        menuService.insert(menu113);

        SysAuthMenu menu114 = new SysAuthMenu();
        menu114.setId("SysOrgGroup");
        menu114.setName("群组管理");
        menu114.setCode("SysOrgGroup");
        menu114.setPath("/sys/org/group");
        menu114.setComp("/sys/org/group/index.vue");
        menu114.setOrnum(4);
        menu114.setPid("SysOrg");
        menu114.setCatag(false);
        menu114.setAvtag(true);
        menu114.setShtag(true);
        menu114.setType("M");
        menuService.insert(menu114);

        SysAuthMenu menu12 = new SysAuthMenu();
        menu12.setId("SysAuth");
        menu12.setName("权限管理");
        menu12.setCode("SysAuth");
        menu12.setComp("LAYOUT");
        menu12.setPath("/sys/auth");
        menu12.setRedirect("/sys/auth/menu");
        menu12.setOrnum(2);
        menu12.setIcon("ant-design:safety-certificate-outlined");
        menu12.setPid("Sys");
        menu12.setAvtag(true);
        menu12.setShtag(true);
        menu12.setType("D");
        menuService.insert(menu12);

        SysAuthMenu menu121 = new SysAuthMenu();
        menu121.setId("SysAuthMenu");
        menu121.setName("菜单管理");
        menu121.setCode("SysAuthMenu");
        menu121.setPath("/sys/auth/menu");
        menu121.setComp("/sys/auth/menu/index.vue");
        menu121.setOrnum(1);
        menu121.setPid("SysAuth");
        menu121.setCatag(false);
        menu121.setAvtag(true);
        menu121.setShtag(true);
        menu121.setType("M");
        menuService.insert(menu121);

        SysAuthMenu menu122 = new SysAuthMenu();
        menu122.setId("SysAuthRole");
        menu122.setName("角色管理");
        menu122.setCode("SysAuthRole");
        menu122.setPath("/sys/auth/role");
        menu122.setComp("/sys/auth/role/index.vue");
        menu122.setOrnum(1);
        menu122.setPid("SysAuth");
        menu122.setCatag(false);
        menu122.setAvtag(true);
        menu122.setShtag(true);
        menu122.setType("M");
        menuService.insert(menu122);

    }

    @Autowired
    private SysAuthMenuService menuService;


}