package com.zsvg.vboot.module.gen.org.rece;

import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.common.util.web.XuserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GenOrgReceService {


    //todo 有时间再优化
    public int update(List<GenOrgRece> reces) {
        String userid = XuserUtil.getUserId();
        for (GenOrgRece rece : reces) {
            rece.setOrgid(rece.getId());
            rece.setId(XstringUtil.getUUID());
            rece.setUserid(userid);
        }

        //1.如果当前数量小于10，则去数据库查询最新的差额记录数
        if (reces.size() < 10) {
            Sqler sqler = new Sqler("t.*", "gen_org_rece", 1, 10 - reces.size());
            sqler.addDescOrder("t.uptim");
            sqler.addEqual("t.userid", userid);
            List<GenOrgRece> list;
            if ("MYSQL".equals(jdbcDao.getDbType())) {
                list = jdbcDao.getTp().query(sqler.getMysqlPagingSql(), sqler.getParams(), new BeanPropertyRowMapper<>(GenOrgRece.class));
            } else {
                list = jdbcDao.getTp().query(sqler.getOraclePagingLowerCaseSql(), sqler.getParams(), new BeanPropertyRowMapper<>(GenOrgRece.class));
            }
            //去重
            for (GenOrgRece dbRece : list) {
                boolean flag = false;
                for (GenOrgRece rece : reces) {
                    if (dbRece.getOrgid().equals(rece.getOrgid())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    reces.add(dbRece);
                }
            }
        }

        //2.清空当前用户的最近使用记录
        jdbcDao.update("delete from gen_org_rece where userid=?", userid);

        //3.更新记录
        repo.saveAll(reces);
        return reces.size();
    }


    @Autowired
    private JdbcDao jdbcDao;


    @Autowired
    private GenOrgReceRepo repo;


}
