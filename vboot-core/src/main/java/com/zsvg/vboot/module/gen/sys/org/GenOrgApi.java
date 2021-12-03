package com.zsvg.vboot.module.gen.sys.org;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.config.security.pojo.Zuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("gen/org")
public class GenOrgApi {
    @GetMapping(value="watag")
    public RestResult getWatag(HttpServletRequest request) {
        Zuser zuser = null;
        String sql = "update sys_org_user set watag=0 where id=?";
        jdbcDao.update(sql, zuser.getId());
        zuser.setWatag(false);
        return RestResult.ok();
    }

    @GetMapping(value="choose")
    public RestResult getChoose(String name, String type) {
        Sqler sqlHelper = new Sqler("id,pid,name","sys_org_main");
        sqlHelper.addWhere("t.avtag = 1");
        sqlHelper.addWhere(XstringUtil.isNotBlank(type),"type in ("+type+")");
        sqlHelper.addLike("name",name);
        sqlHelper.addEqual("t.label", "ekp");
        return RestResult.ok(jdbcDao.findInpList(sqlHelper));
    }

    @GetMapping("search")
    public RestResult getSearch(String name, String type) {
        Sqler sqlHelper = new Sqler("t.id,t.name","sys_org_main");
        sqlHelper.addLeftJoin("t2.name as pid", "sys_org t2", "t2.id=t.pid");
        sqlHelper.addWhere("t.avtag = 1");
        sqlHelper.addLike("t.name",name);
        sqlHelper.addWhere(XstringUtil.isNotBlank(type),"t.type in ("+type+")");
        return RestResult.ok(jdbcDao.findInpList(sqlHelper));
    }


    @GetMapping("dept/{id}")
    public RestResult getDept(@PathVariable String id, String type) {
        Sqler sqlHelper = new Sqler("t.id,t.name","sys_org_main");
        sqlHelper.addLeftJoin("t2.name as pid", "sys_org t2", "t2.id=t.pid");
        sqlHelper.addWhere("t.avtag = 1");
        sqlHelper.addWhere(XstringUtil.isNotBlank(type),"t.type in ("+type+")");
        sqlHelper.addWhere("t.pid = ?",id);
        return RestResult.ok(jdbcDao.findInpList(sqlHelper));
    }

    @Autowired
    private JdbcDao jdbcDao;
}
