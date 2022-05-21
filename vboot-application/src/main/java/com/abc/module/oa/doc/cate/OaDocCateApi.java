package com.abc.module.oa.doc.cate;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("oa/doc/cate")
public class OaDocCateApi {

    private String table = "oa_doc_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addSelect("t.pid,t.crtim,t.uptim");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findTree(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        OaDocCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Ztree> list = service.findTreeList(table,name);
        return RestResult.ok(list);
    }

    @PostMapping
    public RestResult post(@RequestBody OaDocCate main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody OaDocCate main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private OaDocCateService service;

}
