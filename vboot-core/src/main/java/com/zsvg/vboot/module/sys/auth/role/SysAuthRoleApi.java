package com.zsvg.vboot.module.sys.auth.role;

import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/auth/role")
public class SysAuthRoleApi {
    private String table = "sys_auth_role";

    @GetMapping
    public RestResult get(String id, String name) {
        Sqler sqler = new Sqler("t.id,t.crtim,t.name", table);
        sqler.addLeftJoin("o.name as crman", "sys_org o", "o.id=t.crman");
        sqler.addLike("t.name", name);
        sqler.addLike("t.id", id);
        sqler.addOrder("t.crtim desc");
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    @GetMapping("perms")
    public RestResult getPerms(String id, String type) {
        String allSql = "SELECT r.id as \"id\",r.pid as \"pid\",r.name as \"name\",case when rr.id is null then null else 'true' end as \"checked\" FROM sys_auth_perm r LEFT JOIN sys_auth_role_perm rr ON rr.pid=r.id AND rr.id=? where r.cotag = 0 ";
        if ("all".equals(type)) {
            return RestResult.ok(jdbcDao.findMapList(allSql, id));
        } else {
            allSql += " and r.pid like ?";
            return RestResult.ok(jdbcDao.findMapList(allSql, id, type + "%"));
        }
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysAuthRole main = service.findOne(id);
//        main.setPerms(null);
        return RestResult.ok(main);
    }


    @PostMapping
    public RestResult post(@RequestBody SysAuthRole main) {
        service.insert(main);
        jdbcDao.update("update sys_org_user set retag = 0");
        return RestResult.ok();
    }

    @PutMapping
    public RestResult put(@RequestBody SysAuthRole main) {
        service.update(main);
        jdbcDao.update("update sys_org_user set retag = 0");
        return RestResult.ok();
    }

    @PutMapping("flush")
    public RestResult flush() {
        jdbcDao.update("update sys_org_user set retag = 0");
        return RestResult.ok();
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysAuthRoleService service;
}
