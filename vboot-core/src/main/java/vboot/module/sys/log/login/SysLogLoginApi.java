package vboot.module.sys.log.login;

import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/log/login")
public class SysLogLoginApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqlHelper = new Sqler("t.*", "sys_log_login");
        sqlHelper.addLike("t.name", name);
        sqlHelper.addOrder("t.crtim desc");
        return RestResult.ok(jdbcDao.findPageData(sqlHelper));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        return RestResult.ok(repo.findById(id).get());
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids)  {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return RestResult.ok();
    }

    @DeleteMapping("all")
    public RestResult deleteAll()  {
        repo.deleteAll();
        return RestResult.ok();
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysLogLoginRepo repo;
}
