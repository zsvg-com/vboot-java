package vboot.module.sys.perm.menu;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sys/perm/menu")
public class SysPermMenuApi {

    @GetMapping("tree")
    public RestResult getTree(String name, String type) {
        Sqler sqler = new Sqler("sys_perm_menu");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.ornum");
        sqler.addWhere("t.avtag=1");
        sqler.addSelect("t.type,t.crtim,t.uptim,t.pid");
        if(XstringUtil.isNotBlank(type)){
            if("DM".equals(type)){
                sqler.addWhere("t.type in ('D','M')");
            }else{
                return RestResult.ok(new ArrayList<>());
            }
        }
        List<SysPermMenu> list = service.findTree(sqler);
        return RestResult.ok(list);
    }

    //排除了自己
    @GetMapping("listn")
    public RestResult getListn( String id) {
        List<SysPermMenu> list = service.findWithoutItself(id);
        return RestResult.ok(list);
    }

    @GetMapping("one/{id}")
    public RestResult id(@PathVariable String id, HttpServletRequest request) {
        SysPermMenu main = service.findById(id);
        if(main.getPid()!=null){
            SysPermMenu parent = service.findById(main.getPid());
            main.setPname(parent.getName());
        }
        return RestResult.ok(main);
    }


    @PostMapping
    public RestResult post(@RequestBody SysPermMenu main) {
        System.out.println("插入");
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysPermMenu main) {
        System.out.println("修改");
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        System.out.println(ids);
        return RestResult.ok(service.delete(ids));
    }


    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysPermMenuService service;


}
