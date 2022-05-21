package com.abc.module.te.allot.param;

import com.abc.module.te.prod.model.Zscreen;
import com.abc.module.te.prod.model.Zparam;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeAllotParamService extends BaseMainService<TeAllotParam> {

    public List<Zscreen> findData(String catid){
        Sqler sqler2=new Sqler("te_feat_screen");
        sqler2.addEqual("t.catid", catid);
        sqler2.addOrder("t.ornum");
        List<Zscreen> zscreens=jdbcDao.getTp().query(sqler2.getSql(),sqler2.getParams(), new BeanPropertyRowMapper<>(Zscreen.class));

        List<Zparam> zparams = new ArrayList<>();
        String sql3 = "select id,name,code,scrid,ornum from te_feat_param where scrid in (";
        String ins = "";
        for (Zscreen zscreen : zscreens) {
            ins += "'" + zscreen.getId() + "',";
        }
        if (!"".equals(ins)) {
            ins = ins.substring(0, ins.length() - 1);
            sql3 += ins + ") order by scrid,ornum";
            zparams=jdbcDao.getTp().query(sql3, new BeanPropertyRowMapper<>(Zparam.class));
        }

        for (Zscreen zscreen : zscreens) {
            for (Zparam zparam : zparams) {
                if(zparam.getScrid().equals(zscreen.getId())){
                    zscreen.getParams().add(zparam);
                }
            }
        }
        return zscreens;
    }


    //----------bean注入------------
    @Autowired
    private TeAllotParamRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
