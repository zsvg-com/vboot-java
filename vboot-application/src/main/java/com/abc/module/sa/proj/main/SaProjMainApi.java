package com.abc.module.sa.proj.main;

import com.abc.module.sa.prod.group.SaProdGroup;
import com.abc.module.sa.prod.group.SaProdGroupService;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("sa/proj/main")
public class SaProjMainApi {

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Zinp> list = new ArrayList<>();
        Zinp zinp = new Zinp();
        zinp.setId("11");
        zinp.setName("测试1");

        Zinp zinp2 = new Zinp();
        zinp2.setId("22");
        zinp2.setName("测试2");
        list.add(zinp);
        list.add(zinp2);
        return RestResult.ok(list);
    }


    //分页查询项目清单
    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sa_proj_main");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.addre,t.notes");
        System.out.println(new Date());
        return RestResult.ok(service.findPageData(sqler));
    }

    //查询单个项目
    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SaProjMain main = service.findOne(id);
        List<SaProdGroup> groups = groupService.findAllByProjid(id);
        main.setGroups(groups);
        return RestResult.ok(main);
    }

    //新增项目
    @PostMapping
    public RestResult post(@RequestBody SaProjMain main) {
        return RestResult.ok(service.insert(main));
    }

    //修改项目
    @PutMapping
    public RestResult put(@RequestBody SaProjMain main) {
        return RestResult.ok(service.update(main));
    }

    //删除项目
    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SaProjMainService service;

    @Autowired
    private SaProdGroupService groupService;

}
