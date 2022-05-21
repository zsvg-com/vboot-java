package vboot.modulex.oa.flow.cate;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("oa/flow/cate")
public class OaFlowCateApi {

    private String table = "oa_flow_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes");
        sqler.addLeftJoin("c.name panam","oa_flow_cate c","c.id=t.pid");
        sqler.addOrder("t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        OaFlowCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Ztree> list = service.findTreeList(table,name);
        return RestResult.ok(list);
    }

    @PostMapping
    public RestResult post(@RequestBody OaFlowCate main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody OaFlowCate main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private OaFlowCateService service;

}
