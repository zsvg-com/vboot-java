package com.abc.module.te.allot.link;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeAllotLinkService extends BaseMainService<TeAllotLink> {

    public List<Zlink> findData(String catid) {
        Sqler sqler = new Sqler("te_feat_link");
        sqler.addEqual("t.catid", catid);
        sqler.addOrder("t.ornum");
        List<Zlink> list = jdbcDao.getTp().query(sqler.getSql(), sqler.getParams(), new BeanPropertyRowMapper<>(Zlink.class));
        return list;
    }

    //----------bean注入------------
    @Autowired
    private TeAllotLinkRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
