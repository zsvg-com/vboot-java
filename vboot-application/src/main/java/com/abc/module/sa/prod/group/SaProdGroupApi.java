package com.abc.module.sa.prod.group;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sa/prod/group")
public class SaProdGroupApi {

    //分页查询项目清单
    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sa_prod_group");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.addre,t.notes");
        return RestResult.ok(service.findPageData(sqler));
    }

    //查询单个项目
    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SaProdGroup main = service.findOne(id);
        return RestResult.ok(main);
    }

//    //查询单个项目
//    @GetMapping("proj/{id}")
//    public RestResult getOneByProj(@PathVariable String id) {
//        List<SaProdGroup> groups = service.findAllByProjid(id);
//        return RestResult.ok(groups);
//    }


    //新增项目
    @PostMapping
    public RestResult post(@RequestBody SaProdGroup main) {
        return RestResult.ok(service.insert(main));
    }

    //修改项目
    @PutMapping
    public RestResult put(@RequestBody SaProdGroup main) {
        return RestResult.ok(service.update(main));
    }

    //删除项目
    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SaProdGroupService service;

}
