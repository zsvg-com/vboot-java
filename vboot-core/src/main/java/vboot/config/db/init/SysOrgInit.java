package vboot.config.db.init;

import vboot.common.mvc.dao.JdbcDao;
import vboot.common.util.lang.XstringUtil;
import vboot.module.sys.org.dept.SysOrgDept;
import vboot.module.sys.org.dept.SysOrgDeptService;
import vboot.module.sys.org.root.SysOrg;
import vboot.module.sys.org.user.SysOrgUser;
import vboot.module.sys.org.user.SysOrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SysOrgInit {


    //公司及子公司初始化
    protected void initCorp() throws Exception {
        List<SysOrgDept> deptList = new ArrayList<>();
        initDept(deptList,"刘", "LIU",1);
        initDept(deptList,"陈", "CHEN",2);
        initDept(deptList,"张", "ZHANG",3);
        initDept(deptList,"李", "LI",4);
        initDept(deptList,"王", "WANG",5);
        initDept(deptList,"赵", "ZHAO",6);
        initDept(deptList,"孙", "SUN",7);
        initDept(deptList,"周", "ZHOU",8);
        initDept(deptList,"吴", "WU",9);
        initDept(deptList,"郑", "ZHENG",10);
        deptService.insertAll(deptList);
    }

    //部门初始化
    private void initDept(List<SysOrgDept> deptList,String name, String id,Integer ornum) throws Exception {
        SysOrgDept corp = new SysOrgDept();
        corp.setType(1);
        corp.setId(id);
        corp.setName(name + "家科技");
        corp.setLabel(id);
        corp.setOrnum(ornum);
        corp.setAvtag(true);
        corp.setTier("x"+id+"x");
        deptList.add(corp);

        SysOrgDept dept1 = new SysOrgDept();
        dept1.setType(2);
        dept1.setId(id + "-1");
        dept1.setName(name + "一总金办");
        dept1.setLabel(id);
        dept1.setParent(new SysOrg(id));
        dept1.setOrnum(1);
        dept1.setAvtag(true);
        dept1.setTier("x"+id+"x"+id + "-1"+"x");
        deptList.add(dept1);

        SysOrgDept dept2 = new SysOrgDept();
        dept2.setType(2);
        dept2.setId(id + "-2");
        dept2.setName(name + "二人事部");
        dept2.setLabel(id);
        dept2.setParent(new SysOrg(id));
        dept2.setOrnum(2);
        dept2.setAvtag(true);
        dept2.setTier("x"+id+"x"+id + "-2"+"x");
        deptList.add(dept2);

        SysOrgDept dept3 = new SysOrgDept();
        dept3.setType(2);
        dept3.setId(id + "-3");
        dept3.setName(name + "三销售部");
        dept3.setLabel(id);
        dept3.setParent(new SysOrg(id));
        dept3.setOrnum(3);
        dept3.setAvtag(true);
        dept3.setTier("x"+id+"x"+id + "-3"+"x");
        deptList.add(dept3);

        SysOrgDept dept4 = new SysOrgDept();
        dept4.setType(2);
        dept4.setId(id + "-4");
        dept4.setName(name + "四财务部");
        dept4.setLabel(id);
        dept4.setParent(new SysOrg(id));
        dept4.setOrnum(4);
        dept4.setAvtag(true);
        dept4.setTier("x"+id+"x"+id + "-4"+"x");
        deptList.add(dept4);

        SysOrgDept dept5 = new SysOrgDept();
        dept5.setType(2);
        dept5.setId(id + "-5");
        dept5.setName(name + "五采购部");
        dept5.setLabel(id);
        dept5.setParent(new SysOrg(id));
        dept5.setOrnum(5);
        dept5.setAvtag(true);
        dept5.setTier("x"+id+"x"+id + "-5"+"x");
        deptList.add(dept5);

        SysOrgDept dept6 = new SysOrgDept();
        dept6.setType(2);
        dept6.setId(id + "-6");
        dept6.setName(name + "六技术部");
        dept6.setLabel(id);
        dept6.setParent(new SysOrg(id));
        dept6.setOrnum(6);
        dept6.setAvtag(true);
        dept6.setTier("x"+id+"x"+id + "-6"+"x");
        deptList.add(dept6);

        SysOrgDept dept7 = new SysOrgDept();
        dept7.setType(2);
        dept7.setId(id + "-7");
        dept7.setName(name + "七制造部");
        dept7.setLabel(id);
        dept7.setParent(new SysOrg(id));
        dept7.setOrnum(7);
        dept7.setAvtag(true);
        dept7.setTier("x"+id+"x"+id + "-7"+"x");
        deptList.add(dept7);

        SysOrgDept dept8 = new SysOrgDept();
        dept8.setType(2);
        dept8.setId(id + "-8");
        dept8.setName(name + "八法务部");
        dept8.setLabel(id);
        dept8.setParent(new SysOrg(id));
        dept8.setOrnum(8);
        dept8.setAvtag(true);
        dept8.setTier("x"+id+"x"+id + "-8"+"x");
        deptList.add(dept8);

        SysOrgDept dept9 = new SysOrgDept();
        dept9.setType(2);
        dept9.setId(id + "-9");
        dept9.setName(name + "九信息部");
        dept9.setLabel(id);
        dept9.setParent(new SysOrg(id));
        dept9.setOrnum(9);
        dept9.setAvtag(true);
        dept9.setTier("x"+id+"x"+id + "-9"+"x");
        deptList.add(dept9);

        for (int i = 1; i <= 50; i++) {
            String RSID8= XstringUtil.getRSID8();
            SysOrgDept xdept = new SysOrgDept();
            xdept.setType(2);
            xdept.setId(id + "--"+i);
            xdept.setName(RSID8);
            xdept.setLabel(id);
            xdept.setParent(new SysOrg(id+"-"+(i%10==0?1:i%10)));
            xdept.setAvtag(true);
            xdept.setTier("x"+id+"x"+id+"-"+(i%10==0?1:i%10)+"x"+id + "--"+i+"x");
            deptList.add(xdept);
        }

    }

    protected void initUser() {
        List<SysOrgUser> userList = new ArrayList<>();
        initUser(userList,"刘", "liu", "LIU");
        initUser(userList,"陈", "c", "CHEN");
        initUser(userList,"张", "z", "ZHANG");
        initUser(userList,"李", "l", "LI");
        initUser(userList,"王", "w", "WANG");
        initUser(userList,"赵", "zhao", "ZHAO");
        initUser(userList,"孙", "s", "SUN");
        initUser(userList,"周", "zhou", "ZHOU");
        initUser(userList,"吴", "wu", "WU");
        initUser(userList,"郑", "zheng", "ZHENG");
        userService.insertAll(userList);
    }

    private void initUser(List<SysOrgUser> userList,String name, String id, String pid) {
        SysOrgUser user0 = new SysOrgUser();
        user0.setId(id+"lao");
        user0.setUsnam(id);
        user0.setName(name + "老");
        user0.setDept(new SysOrg(pid));
        user0.setPacod("1");//1
        user0.setAvtag(true);
        user0.setTier("x"+pid+"x"+id+"lao");
        userList.add(user0);

        for (int i = 1; i <= 9; i++) {
            SysOrgUser userx = new SysOrgUser();
            userx.setId(id + i);
            userx.setUsnam(id + i);
            userx.setName(name + numChange("" + i));
            userx.setDept(new SysOrg(pid + "-" + i));
            userx.setOrnum(i);
            userx.setPacod("1");//1
            userx.setAvtag(true);
            userx.setTier("x"+pid+"x"+pid + "-" + i+"x"+id + i+"x");
            userList.add(userx);

            for (int j = 1; j <= 9; j++) {
                SysOrgUser usery = new SysOrgUser();
                usery.setId(id + i + j);
                usery.setUsnam(id + i + j);
                usery.setName(name + numChange("" + i) + numChange("" + j));
                usery.setDept(new SysOrg(pid + "-" + i));
                usery.setOrnum(j);
                usery.setPacod("1");//1
                usery.setAvtag(true);
                usery.setTier("x"+pid+"x"+pid + "-" + i+"x"+id + i + j+"x");
                userList.add(usery);
            }
        }
    }


    protected void initZsf() {

        SysOrgDept dept1 = new SysOrgDept();
        dept1.setType(2);
        dept1.setId("ZHANG-3-1");
        dept1.setName("张三销售一室");
        dept1.setLabel("ZHANG");
        dept1.setParent(new SysOrg("ZHANG-3"));
        dept1.setOrnum(1);
        dept1.setAvtag(true);
        dept1.setTier("xZHANGxZHANG-3xZHANG-3-1x");
        deptService.insert(dept1);

        SysOrgDept dept2 = new SysOrgDept();
        dept2.setType(2);
        dept2.setId("ZHANG-3-2");
        dept2.setName("张三销售二室");
        dept2.setLabel("ZHANG");
        dept2.setParent(new SysOrg("ZHANG-3"));
        dept2.setOrnum(1);
        dept2.setAvtag(true);
        dept2.setTier("xZHANGxZHANG-3xZHANG-3-2x");
        deptService.insert(dept2);

        SysOrgUser user4 = new SysOrgUser();
        user4.setId("zsf");
        user4.setUsnam("zsf");
        user4.setName("张三丰");
        user4.setDept(new SysOrg("ZHANG-3-2"));
        user4.setOrnum(1);
        user4.setPacod("1");
        user4.setAvtag(true);
        user4.setTier("xZHANGxZHANG-3xZHANG-3-2xzsfx");
        userService.insert(user4);
    }

    protected void initSa() {
        SysOrgUser user = new SysOrgUser();
        user.setId("sa");
        user.setUsnam("sa");
        user.setName("管理员");
        user.setPacod("1");
        user.setAvtag(true);
        user.setTier("xsax");
        userService.insert(user);

        SysOrgUser user2 = new SysOrgUser();
        user2.setId("vben");
        user2.setUsnam("vben");
        user2.setName("小维");
        user2.setPacod("123456");
        user2.setAvtag(true);
        user2.setTier("xvbenx");
        userService.insert(user2);
    }

    private String numChange(String num) {
        switch (num) {
            case "1":
                return "一";
            case "2":
                return "二";
            case "3":
                return "三";
            case "4":
                return "四";
            case "5":
                return "五";
            case "6":
                return "六";
            case "7":
                return "七";
            case "8":
                return "八";
            case "9":
                return "九";
            case "10":
                return "十";
        }
        return "零";
    }

//
//    //岗位初始化
//    private void initPost() {
//
//    }
//
//    //组织架构组初始化
//    private void initGroup() {
//
//    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysOrgDeptService deptService;

    @Autowired
    private SysOrgUserService userService;
}
