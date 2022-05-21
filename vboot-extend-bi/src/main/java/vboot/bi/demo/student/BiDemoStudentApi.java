package vboot.bi.demo.student;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("bi/demo/student")
public class BiDemoStudentApi {

    private String table = "bi_demo_student";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        BiDemoStudent main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody BiDemoStudent main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody BiDemoStudent main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private BiDemoStudentService service;

}