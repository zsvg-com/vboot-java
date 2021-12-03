package com.zsvg.vboot.config.db.init;

import com.zsvg.vboot.module.sys.org.user.SysOrgUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;

@Configuration
@Slf4j
public class DbInitListener implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * 首次数据库生成后，初始化组织架构，菜单，权限角色等信息
     * 增加sa，admin管理员账号及相关测试账号
     * 判断依据是sys_org_user是否含有id为sa的账号
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (!userDao.existsById("sa")) {
                System.out.println("首次启动系统，正在进行数据库初始化，请耐心等待。");
                sysOrgInit.initCorp();
                System.out.println("1.1 初始化部门完毕");
                sysOrgInit.initUser();
                sysOrgInit.initZsf();
                sysOrgInit.initSa();
                System.out.println("1.2 初始化用户完毕");
//                sysOrgInit.initPost();
//                sysOrgInit.initGroup();

                sysAuthInit.initMenu();
                System.out.println("2.1 初始化菜单完毕");
            }
        } catch (Exception e) {
            System.err.println("初始化出错");
            e.printStackTrace();
        }
    }

    @Autowired
    private SysOrgUserRepo userDao;


    @Autowired
    private SysOrgInit sysOrgInit;

    @Autowired
    private SysAuthInit sysAuthInit;


}