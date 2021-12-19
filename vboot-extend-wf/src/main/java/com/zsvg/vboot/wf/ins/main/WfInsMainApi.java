package com.zsvg.vboot.wf.ins.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("wf/ins/main")
public class WfInsMainApi {

    private String table = "wf_ins_main";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        WfInsMain main = service.findOne(id);
        if(main.getTemid()!=null){
            Sqler sqler = new Sqler("name","wf_tem_main");
            sqler.addEqual("id",main.getTemid());
            String name = jdbcDao.findOneString(sqler);
            main.setTemna(name);
        }
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody WfInsMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody WfInsMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private WfInsMainService service;

}
