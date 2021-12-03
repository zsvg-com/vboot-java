package com.zsvg.vboot.module.sys.auth.menu;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sys/auth/menu")
public class SysAuthMenuApi {

    @GetMapping("tree")
    public RestResult getTree(String name, String type) {
        Sqler sqler = new Sqler("sys_auth_menu");
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
        List<SysAuthMenu> list = service.findTree(sqler);
        return RestResult.ok(list);
    }

    //排除了自己
    @GetMapping("listn")
    public RestResult getListn( String id) {
        List<SysAuthMenu> list = service.findWithoutItself(id);
        return RestResult.ok(list);
    }

    @GetMapping("one/{id}")
    public RestResult id(@PathVariable String id, HttpServletRequest request) {
        SysAuthMenu main = service.findById(id);
        if(main.getPid()!=null){
            SysAuthMenu parent = service.findById(main.getPid());
            main.setPname(parent.getName());
        }
        return RestResult.ok(main);
    }


    @PostMapping
    public RestResult post(@RequestBody SysAuthMenu main) {
        System.out.println("插入");
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysAuthMenu main) {
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
    private SysAuthMenuService service;


}
