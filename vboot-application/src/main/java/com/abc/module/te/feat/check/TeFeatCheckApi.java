package com.abc.module.te.feat.check;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("te/feat/check")
public class TeFeatCheckApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("te_feat_check");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        sqler.addLeftJoin("c.name as catna", "te_prod_cate c", "c.id=t.catid");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeFeatCheck main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody TeFeatCheck main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeFeatCheck main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeFeatCheckService service;

}
