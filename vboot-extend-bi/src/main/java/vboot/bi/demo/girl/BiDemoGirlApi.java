package vboot.bi.demo.girl;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bi/demo/girl")
public class BiDemoGirlApi {

    private String table = "bi_demo_girl";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        BiDemoGirl main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody BiDemoGirl main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody BiDemoGirl main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private BiDemoGirlService service;

}