package com.zsvg.vboot.module.sys.org.post;

import cn.hutool.core.util.StrUtil;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.module.sys.org.root.SysOrg;
import com.zsvg.vboot.common.mvc.api.PageData;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.module.sys.org.root.SysOrgRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgPostService {

    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }


    public SysOrgPost insert(SysOrgPost main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        if (main.getDept() == null || StrUtil.isBlank(main.getDept().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getDept().getId());
            main.setTier(tier + main.getId() + "x");
        }
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        orgRepo.save(sysOrg);
        return postRepo.save(main);
    }

    public SysOrgPost update(SysOrgPost main) {
        main.setUptim(new Date());
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        if (main.getDept() == null || StrUtil.isBlank(main.getDept().getId())) {
            main.setTier("x" + main.getId() + "x");
        } else {
            String tier = jdbcDao.findOneString("select tier from sys_org_dept where id=?", main.getDept().getId());
            main.setTier(tier + main.getId() + "x");
        }
        SysOrgPost backPost = postRepo.save(main);
        orgRepo.save(sysOrg);
        return backPost;
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            postRepo.deleteById(id);
            orgRepo.deleteById(id);
        }
        return ids.length;
    }

    @Transactional(readOnly = true)
    public SysOrgPost findById(String id) {
        return postRepo.findById(id).get();
    }

    @Autowired
    private JdbcDao jdbcDao;


    @Autowired
    private SysOrgRepo orgRepo;

    @Autowired
    private SysOrgPostRepo postRepo;


}
