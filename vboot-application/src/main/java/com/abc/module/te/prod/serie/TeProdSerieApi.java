package com.abc.module.te.prod.serie;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/prod/serie")
public class TeProdSerieApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("te_prod_serie");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        sqler.addLeftJoin("c.name as catna", "te_prod_cate c", "c.id=t.catid");
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("list")
    public RestResult getList(String catid) {
        return RestResult.ok(service.findAllByCatid(catid));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeProdSerie main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody TeProdSerie main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeProdSerie main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeProdSerieService service;

}
