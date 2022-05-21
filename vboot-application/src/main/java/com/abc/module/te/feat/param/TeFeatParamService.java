package com.abc.module.te.feat.param;

import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.ZidName;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeFeatParamService extends BaseMainService<TeFeatParam> {

    public List<ZidName> findData(String paramid){
        Sqler sqler1 = new Sqler("t.catid","te_feat_screen");
        sqler1.addInnerJoin("", "te_feat_param p", "p.scrid=t.id");
        sqler1.addEqual("p.id", paramid);
        String catid = jdbcDao.findOneString(sqler1);

        Sqler sqler2=new Sqler("te_prod_model");
        sqler2.addEqual("t.catid", catid);
        sqler2.addOrder("t.ornum");
        List<ZidName> zmodels=jdbcDao.getTp().query(sqler2.getSql(),sqler2.getParams(), new BeanPropertyRowMapper<>(ZidName.class));
        return zmodels;
    }




    //----------bean注入------------
    @Autowired
    private TeFeatParamRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
