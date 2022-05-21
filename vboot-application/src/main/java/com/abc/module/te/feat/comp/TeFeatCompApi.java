package com.abc.module.te.feat.comp;

import com.abc.module.te.allot.param.TeAllotParamService;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/feat/comp")
public class TeFeatCompApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("te_feat_comp");
        sqler.addLike("t.name", name);
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeFeatComp main = service.findOne(id);
        if (XstringUtil.isNotBlank(main.getPatid())) {
            main.setPatem(apService.findOne(main.getPatid()));
        }
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody TeFeatComp main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeFeatComp main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeFeatCompService service;

    @Autowired
    private TeAllotParamService apService;

    @Autowired
    private JdbcDao jdbcDao;

}
