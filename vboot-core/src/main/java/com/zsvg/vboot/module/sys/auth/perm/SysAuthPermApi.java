package com.zsvg.vboot.module.sys.auth.perm;

import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.pojo.ZidName;
import com.zsvg.vboot.common.mvc.pojo.Zinp;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys/auth/perm")
public class SysAuthPermApi {

    @GetMapping("choose")
    public RestResult choose(String name) {
        List<ZidName> stores=jdbcDao.getIdNameListByTable("sys_auth_perm",name);
        return RestResult.ok(stores);
    }

    @GetMapping("type")
    public RestResult type(String type) {
        if(XstringUtil.isNotBlank(type)){
            List<Zinp> stores=jdbcDao.findInpList("select id,pid,name from sys_auth_perm where pid like ?",type+"%");
            return RestResult.ok(stores);
        }else{
            List<Zinp> stores=jdbcDao.findInpList("select id,pid,name from sys_auth_perm");
            return RestResult.ok(stores);
        }
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysAuthPerm main=repo.findById(id).get();
        return RestResult.ok(main);
    }

    @PutMapping
    public RestResult put(@RequestBody SysAuthPerm main) {
        repo.save(main);
        return RestResult.ok();
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return RestResult.ok();
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysAuthPermRepo repo;
}
