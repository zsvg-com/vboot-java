package com.abc.module.te.prod.cate;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/prod/cate")
public class TeProdCateApi {

    //分页特性分类
    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("te_prod_cate");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes");
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("list")
    public RestResult getList() {
        return RestResult.ok(service.findAll());
    }

    //查询单个分类
    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeProdCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    //新增分类
    @PostMapping
    public RestResult post(@RequestBody TeProdCate cate) {
        return RestResult.ok(service.insert(cate));
    }

    //修改分类
    @PutMapping
    public RestResult put(@RequestBody TeProdCate cate) {
        return RestResult.ok(service.update(cate));
    }

    //删除分类
    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeProdCateService service;

}
