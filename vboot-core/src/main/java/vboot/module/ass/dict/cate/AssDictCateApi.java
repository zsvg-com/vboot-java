package vboot.module.ass.dict.cate;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ass/dict/cate")
public class AssDictCateApi {

    private String table = "ass_dict_cate";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes,t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        AssDictCate cate = service.findOne(id);
        return RestResult.ok(cate);
    }

    @PostMapping
    public RestResult post(@RequestBody AssDictCate main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody AssDictCate main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private AssDictCateService service;

}
