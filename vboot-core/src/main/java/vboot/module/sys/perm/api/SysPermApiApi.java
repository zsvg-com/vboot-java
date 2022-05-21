package vboot.module.sys.perm.api;

import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.pojo.ZidName;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sys/perm/api")
public class SysPermApiApi {

    @GetMapping("list")
    public RestResult list(String name) {
        List<ZidName> stores=jdbcDao.getIdNameListByTable("sys_perm_api",name);
        return RestResult.ok(stores);
    }

    @GetMapping("type")
    public RestResult type(String type) {
        if(XstringUtil.isNotBlank(type)){
            List<Zinp> stores=jdbcDao.findInpList("select id,pid,name from sys_perm_api where pid like ?",type+"%");
            return RestResult.ok(stores);
        }else{
            List<Zinp> stores=jdbcDao.findInpList("select id,pid,name from sys_perm_api");
            return RestResult.ok(stores);
        }
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysPermApi main=repo.findById(id).get();
        return RestResult.ok(main);
    }

    @PutMapping
    public RestResult put(@RequestBody SysPermApi main) {
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
    private SysPermApiRepo repo;
}
