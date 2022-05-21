package vboot.module.sys.org.user;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys/org/user")
public class SysOrgUserApi {

    private String table = "sys_org_user";

    @GetMapping
    public RestResult get(String deptid, String name, String usnam, String ninam, String monum) {
        Sqler sqler = new Sqler(table);
        if (XstringUtil.isNotBlank(name)||XstringUtil.isNotBlank(usnam)||XstringUtil.isNotBlank(ninam)||XstringUtil.isNotBlank(monum)) {
            sqler.addLike("t.name", name);
            sqler.addLike("t.usnam", usnam);
            sqler.addLike("t.ninam", ninam);
            sqler.addLike("t.monum", monum);
        } else if (XstringUtil.isNotBlank(deptid)){
            sqler.addEqual("t.deptid", deptid);
        }
        sqler.addSelect("t.crtim,t.uptim,t.deptid,t.notes");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id, HttpServletRequest request) {
        SysOrgUser main = service.findById(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SysOrgUser main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysOrgUser main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }


    @Autowired
    private SysOrgUserService service;

}
