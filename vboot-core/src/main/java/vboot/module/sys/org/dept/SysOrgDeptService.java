package vboot.module.sys.org.dept;

import cn.hutool.core.util.StrUtil;
import vboot.module.sys.org.root.SysOrg;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.ZidName;
import vboot.common.mvc.api.PageData;
import vboot.common.util.lang.XstringUtil;
import vboot.module.sys.org.root.SysOrgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgDeptService {

    @Transactional(readOnly = true)
    public SysOrgDept findById(String id) {

        return deptRepo.findById(id).get();
    }

    public void insertAll(List<SysOrgDept> list) {
        List<SysOrg> orgList = new ArrayList<>();
        for (SysOrgDept main : list) {
            if (main.getId() == null || "".equals(main.getId())) {
                main.setId(XstringUtil.getUUID());
            }
            if (main.getParent() == null || StrUtil.isBlank(main.getParent().getId())) {
                main.setTier("x" + main.getId() + "x");
            } else {
                String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getParent().getId());
                main.setTier(tier + main.getId() + "x");
            }
            SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
            orgList.add(sysOrg);
        }
        orgRepo.saveAll(orgList);
        deptRepo.saveAll(list);
    }

    public SysOrgDept insert(SysOrgDept main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        if (main.getParent() == null || StrUtil.isBlank(main.getParent().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getParent().getId());
            main.setTier(tier + main.getId() + "x");
        }
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        orgRepo.save(sysOrg);
        return deptRepo.save(main);
    }


    public SysOrgDept update(SysOrgDept main) throws Exception {
        main.setUptim(new Date());
        if (main.getParent() == null || StrUtil.isBlank(main.getParent().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getParent().getId());
            main.setTier(tier + main.getId() + "x");
            String[] arr = tier.split("x");
            for (String str : arr) {
                if (main.getId().equals(str)) {
                    throw new Exception("父部门不能为自己或者自己的子部门");
                }
            }
        }
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        SysOrgDept backDept = deptRepo.save(main);
        String oldTier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getId());
        dealDeptTier(oldTier, main.getTier(), main.getId());
        dealUserTier(oldTier, main.getTier());
        dealPostTier(oldTier, main.getTier());
        orgRepo.save(sysOrg);
        return backDept;
    }

    public int delete(String[] ids) {
        for (String str : ids) {
            deptRepo.deleteById(str);
            orgRepo.deleteById(str);
        }
        return ids.length;
    }

    private void dealDeptTier(String oldTier, String newTier, String id) {
        String sql = "select id,tier as name from sys_org_dept where tier like ? and id<>?";
        List<ZidName> list = jdbcDao.findIdNameList(sql, oldTier + "%", id);
        String updateSql = "update sys_org_dept set tier=? where id=?";
        List<Object[]> updateList = new ArrayList<Object[]>();
        batchReady(oldTier, newTier, list, updateList);
        jdbcDao.batch(updateSql, updateList);
    }

    private void dealUserTier(String oldTier, String newTier) {
        String sql = "select id,tier as name from sys_org_user where tier like ?";
        List<ZidName> list = jdbcDao.findIdNameList(sql, oldTier + "%");
        String updateSql = "update sys_org_user set tier=? where id=?";
        List<Object[]> updateList = new ArrayList<>();
        batchReady(oldTier, newTier, list, updateList);
        jdbcDao.batch(updateSql, updateList);
    }

    private void dealPostTier(String oldTier, String newTier) {
        String sql = "select id,tier as name from sys_org_post where tier like ?";
        List<ZidName> list = jdbcDao.findIdNameList(sql, oldTier + "%");
        String updateSql = "update sys_org_post set tier=? where id=?";
        List<Object[]> updateList = new ArrayList<>();
        batchReady(oldTier, newTier, list, updateList);
        jdbcDao.batch(updateSql, updateList);
    }

    private void batchReady(String oldTier, String newTier, List<ZidName> list, List<Object[]> updateList) {
        for (ZidName ztwo : list) {
            Object[] arr = new Object[2];
            arr[1] = ztwo.getId();
            arr[0] = ztwo.getName().replace(oldTier, newTier);
            updateList.add(arr);
        }
    }


    public List<SysOrgDept> findAll(Sqler sqler) {
        List<SysOrgDept> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(SysOrgDept.class));
        return list;
    }

    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    public List<SysOrgDept> findTree(Sqler sqler) {
        List<SysOrgDept> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(SysOrgDept.class));
        return buildByRecursive(list);
    }

    public List<SysOrgDept> findWithoutItself(Sqler sqler, String id) {
        List<SysOrgDept> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(SysOrgDept.class));
        return buildByRecursiveWithoutItself(list, id);
    }


    //使用递归方法建树
    private List<SysOrgDept> buildByRecursive(List<SysOrgDept> nodes) {
        List<SysOrgDept> list = new ArrayList<>();
        for (SysOrgDept node : nodes) {
            if (node.getPid() == null) {
                list.add(findChildrenByTier(node, nodes));
            } else {
                boolean flag = false;
                for (SysOrgDept node2 : nodes) {
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
    private SysOrgDept findChildrenByTier(SysOrgDept node, List<SysOrgDept> nodes) {
        for (SysOrgDept item : nodes) {
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
    private List<SysOrgDept> buildByRecursiveWithoutItself(List<SysOrgDept> nodes, String id) {
        List<SysOrgDept> list = new ArrayList<>();
        for (SysOrgDept node : nodes) {
            if (node.getPid() == null && !node.getId().equals(id)) {
                list.add(findChildrenByTierWithoutItself(node, nodes, id));
            } else {
                boolean flag = false;
                for (SysOrgDept node2 : nodes) {
                    if (node.getPid() != null && node.getPid().equals(node2.getId())) {
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
    private SysOrgDept findChildrenByTierWithoutItself(SysOrgDept node, List<SysOrgDept> nodes, String id) {
        for (SysOrgDept item : nodes) {
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
    private JdbcDao jdbcDao;


    @Autowired
    private SysOrgDeptRepo deptRepo;


    @Autowired
    private SysOrgRepo orgRepo;
}
