package vboot.module.sys.org.user;

import cn.hutool.core.util.StrUtil;
import vboot.module.sys.org.root.SysOrg;
import vboot.module.sys.org.root.SysOrgRepo;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.api.PageData;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgUserService {


    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    public void insertAll(List<SysOrgUser> list) {
        List<SysOrg> orgList = new ArrayList<>();
        for (SysOrgUser main : list) {
            if (main.getId() == null || "".equals(main.getId())) {
                main.setId(XstringUtil.getUUID());
            }
            if (main.getDept() == null || StrUtil.isBlank(main.getDept().getId())) {
                main.setTier("x" + main.getId() + "x");
            } else {
                String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getDept().getId());
                main.setTier(tier + main.getId() + "x");
            }
            main.setPacod(DigestUtils.md5DigestAsHex(("abc" + main.getPacod() + "xyz").getBytes()));
            SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
            orgList.add(sysOrg);
        }
        orgRepo.saveAll(orgList);
        userRepo.saveAll(list);
    }


    public SysOrgUser insert(SysOrgUser main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        if (main.getDept() == null || StrUtil.isBlank(main.getDept().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getDept().getId());
            main.setTier(tier + main.getId() + "x");
        }
        main.setPacod(DigestUtils.md5DigestAsHex(("abc" + main.getPacod() + "xyz").getBytes()));
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        orgRepo.save(sysOrg);
        return userRepo.save(main);
    }


    public SysOrgUser update(SysOrgUser main) {
        main.setUptim(new Date());
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        if (main.getDept() == null || StrUtil.isBlank(main.getDept().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getDept().getId());
            main.setTier(tier + main.getId() + "x");
        }
        SysOrgUser backUser = userRepo.save(main);
        orgRepo.save(sysOrg);
        return backUser;
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            userRepo.deleteById(id);
            orgRepo.deleteById(id);
        }
        return ids.length;
    }

    @Transactional(readOnly = true)
    public SysOrgUser findById(String id) {
        return userRepo.findById(id).get();
    }


    @Autowired
    private SysOrgRepo orgRepo;

    @Autowired
    private SysOrgUserRepo userRepo;

    @Autowired
    private JdbcDao jdbcDao;

}
