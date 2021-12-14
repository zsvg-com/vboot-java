package com.zsvg.vboot.wf.tem.cate;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wf/tem/cate")
public class WfTemCateApi {

    private String table = "wf_tem_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes,t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        WfTemCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Ztree> list = service.findTreeList(table,name);
        return RestResult.ok(list);
    }

    @PostMapping
    public RestResult post(@RequestBody WfTemCate main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody WfTemCate main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private WfTemCateService service;

}
