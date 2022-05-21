package vboot.module.sys.org.dept;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("sys/org/dept")
public class SysOrgDeptApi {

    private String table = "sys_org_dept";

    @GetMapping
    public RestResult get(String name, String pid,String notes) {
        Sqler sqler = new Sqler(table);
        if (XstringUtil.isNotBlank(name)) {
            sqler.addLike("t.name", name);
        } else {
            if("".equals(pid)){
                sqler.addWhere("t.pid is null");
            }
            else if (XstringUtil.isNotBlank(pid)) {
                sqler.addEqual("t.pid", pid);
            }
        }
        sqler.addLike("t.notes",notes);
        sqler.addWhere("t.avtag=1");
        sqler.addOrder("t.ornum");
        sqler.addSelect("t.ornum,t.notes,t.pid,t.crtim,t.uptim");
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }


    //排除了自己
//    @GetMapping("listn")
//    public RestResult listn(String name, String id) {
//        Sqler sqler = new Sqler(table);
//        sqler.addLike("t.name", name);
//        sqler.addWhere("t.avtag=1");
//        sqler.addOrder("t.ornum");
//        sqler.addSelect("t.pid");
//        return RestResult.ok(service.findWithoutItself(sqler, id));
//    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id, HttpServletRequest request) {
        SysOrgDept main = service.findById(id);
        return RestResult.ok(main);
    }


    @PostMapping
    public RestResult post(@RequestBody SysOrgDept main) {
//        Thread.sleep(3000);
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysOrgDept main) throws Exception {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysOrgDeptService service;

}
