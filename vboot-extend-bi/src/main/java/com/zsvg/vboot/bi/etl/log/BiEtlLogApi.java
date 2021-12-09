package com.zsvg.vboot.bi.etl.log;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bi/etl/log")
public class BiEtlLogApi {
    private String table="bi_etl_log";


    @GetMapping
    public RestResult list(Integer panum, Integer pasiz, String name, String id) {
        Sqler sqler = new Sqler("t.ID_BATCH as id,t.TRANSNAME as name,t.STARTDATE as sdate,t.ENDDATE as edate,t.STATUS as status,t.LOGDATE as ldate,t.DEPDATE as ddate,t.REPLAYDATE as rdate",
                table,panum,pasiz);
        sqler.addLike("t.TRANSNAME", name);
        sqler.addEqual("t.ID_BATCH", id);
        sqler.addOrder("t.LOGDATE desc");
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult get(@PathVariable String id) {
        String sql = "select t.ID_BATCH as id,t.* from "+table+" t where t.ID_BATCH=?";
        return RestResult.ok(jdbcDao.findMap(sql, id));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        String sql = "delete from "+table+" where ID_BATCH=?";
        for (String id : ids) {
            jdbcDao.update(sql, id);
        }
        return RestResult.ok();
    }

    @Autowired
    private JdbcDao jdbcDao;

}
