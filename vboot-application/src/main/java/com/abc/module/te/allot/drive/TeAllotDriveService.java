package com.abc.module.te.allot.drive;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeAllotDriveService extends BaseMainService<TeAllotDrive> {

    public List<Zdrive> findData(String catid) {
        Sqler sqler = new Sqler("te_feat_drive");
        sqler.addEqual("t.catid", catid);
        sqler.addOrder("t.ornum");
        List<Zdrive> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(Zdrive.class));
        return list;
    }

    //----------bean注入------------
    @Autowired
    private TeAllotDriveRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
