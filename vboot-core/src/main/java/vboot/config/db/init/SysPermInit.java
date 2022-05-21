package vboot.config.db.init;

import vboot.module.sys.perm.menu.SysPermMenu;
import vboot.module.sys.perm.menu.SysPermMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysPermInit {

    protected void initMenu() {
        SysPermMenu menu1 = new SysPermMenu();
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

        SysPermMenu menu11 = new SysPermMenu();
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

        SysPermMenu menu111 = new SysPermMenu();
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

        SysPermMenu menu112 = new SysPermMenu();
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

        SysPermMenu menu113 = new SysPermMenu();
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

        SysPermMenu menu114 = new SysPermMenu();
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

        SysPermMenu menu12 = new SysPermMenu();
        menu12.setId("SysPerm");
        menu12.setName("权限管理");
        menu12.setCode("SysPerm");
        menu12.setComp("LAYOUT");
        menu12.setPath("/sys/perm");
        menu12.setRedirect("/sys/perm/menu");
        menu12.setOrnum(2);
        menu12.setIcon("ant-design:safety-certificate-outlined");
        menu12.setPid("Sys");
        menu12.setAvtag(true);
        menu12.setShtag(true);
        menu12.setType("D");
        menuService.insert(menu12);

        SysPermMenu menu121 = new SysPermMenu();
        menu121.setId("SysPermMenu");
        menu121.setName("菜单管理");
        menu121.setCode("SysPermMenu");
        menu121.setPath("/sys/perm/menu");
        menu121.setComp("/sys/perm/menu/index.vue");
        menu121.setOrnum(1);
        menu121.setPid("SysPerm");
        menu121.setCatag(false);
        menu121.setAvtag(true);
        menu121.setShtag(true);
        menu121.setType("M");
        menuService.insert(menu121);

        SysPermMenu menu122 = new SysPermMenu();
        menu122.setId("SysPermRole");
        menu122.setName("角色管理");
        menu122.setCode("SysPermRole");
        menu122.setPath("/sys/perm/role");
        menu122.setComp("/sys/perm/role/index.vue");
        menu122.setOrnum(1);
        menu122.setPid("SysPerm");
        menu122.setCatag(false);
        menu122.setAvtag(true);
        menu122.setShtag(true);
        menu122.setType("M");
        menuService.insert(menu122);

    }

    @Autowired
    private SysPermMenuService menuService;


}