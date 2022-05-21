package com.abc.module.oa.doc.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("oa/doc/main")
public class OaDocMainApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("oa_doc_main");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }


    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        OaDocMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody OaDocMain main) throws DocumentException {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody OaDocMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private OaDocMainService service;

}
