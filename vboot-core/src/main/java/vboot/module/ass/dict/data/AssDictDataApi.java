package vboot.module.ass.dict.data;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("ass/dict/data")
public class AssDictDataApi {

    private String table = "ass_dict_data";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes,t.ornum,t.code");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        AssDictData main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody AssDictData main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody AssDictData main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private AssDictDataService service;

}
