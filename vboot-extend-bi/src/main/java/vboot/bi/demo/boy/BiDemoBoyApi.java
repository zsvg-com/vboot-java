package vboot.bi.demo.boy;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("bi/demo/boy")
public class BiDemoBoyApi {

    private String table = "bi_demo_boy";

    @GetMapping
    public RestResult get(String name) {
        List<BiDemoBoy> boys = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            BiDemoBoy boy=new BiDemoBoy();
            boy.setId(XstringUtil.getUUID());
            boy.setName(XstringUtil.getRSID8());
            boys.add(boy);
        }
        repo.saveAll(boys);
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        BiDemoBoy main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody BiDemoBoy main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody BiDemoBoy main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private BiDemoBoyService service;

    @Autowired
    private BiDemoBoyRepo repo;

}