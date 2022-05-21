package vboot.module.sys.org.group;

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

import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgGroupService {

    @Transactional(readOnly = true)
    public SysOrgGroup findById(String id) {
        return groupRepo.findById(id).get();
    }

    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    public SysOrgGroup insert(SysOrgGroup main) {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        orgRepo.save(sysOrg);
        return groupRepo.save(main);
    }


    public SysOrgGroup update(SysOrgGroup main) {
        main.setUptim(new Date());
        SysOrg sysOrg = new SysOrg(main.getId(), main.getName());
        orgRepo.save(sysOrg);
        return groupRepo.save(main);
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            groupRepo.deleteById(id);
            orgRepo.deleteById(id);
        }
        return ids.length;
    }


    public List<SysOrgGroup> findAll(String name) {
        List<SysOrgGroup> list;
        if (StrUtil.isNotBlank(name)) {
            list = groupRepo.findByNameLikeOrderByOrnum("%" + name + "%");
        } else {
            list = groupRepo.findByOrderByOrnum();
        }
        return list;
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysOrgGroupRepo groupRepo;

    @Autowired
    private SysOrgRepo orgRepo;

}
