package com.abc.module.te.allot.check;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeAllotCheckService extends BaseMainService<TeAllotCheck> {

    public List<Zcheck> findData(String catid) {
        Sqler sqler = new Sqler("te_feat_check");
        sqler.addEqual("t.catid", catid);
        sqler.addOrder("t.ornum");
        List<Zcheck> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(Zcheck.class));
        return list;
    }


    //----------bean注入------------
    @Autowired
    private TeAllotCheckRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
