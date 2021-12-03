package com.zsvg.vboot.module.ass.num.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ass/num/main")
public class AssNumMainApi {


    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("t.*","as_num_main");
        sqler.addLike("t.name",name);
        return RestResult.ok(service.findPageData(sqler)) ;
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        AssNumMain main=repo.findById(id).get();
        return RestResult.ok(main);
    }

    @PostMapping
    public synchronized RestResult post(@RequestBody AssNumMain main) {
        if(repo.existsById(main.getId())){
            return RestResult.build(201,"编号已存在，请修改编号");
        }
        main.setNflag(true);
        repo.save(main);
        return RestResult.ok();
    }

    @PutMapping
    public RestResult pust(@RequestBody AssNumMain main) {
        if(XstringUtil.isBlank(main.getNunex())){
            main.setNflag(true);
        }
        repo.save(main);
        return RestResult.ok();
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return RestResult.ok();
    }

    @Autowired
    private AssNumMainRepo repo;

    @Autowired
    private AssNumMainService service;
}
