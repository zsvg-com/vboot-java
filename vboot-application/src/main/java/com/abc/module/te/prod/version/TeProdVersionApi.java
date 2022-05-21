package com.abc.module.te.prod.version;

import com.abc.module.te.allot.param.TeAllotParamService;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("te/prod/version")
public class TeProdVersionApi {

    @GetMapping
    public RestResult get(String name, String modid) {
        Sqler sqler = new Sqler("te_prod_version");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.modid", modid);
        sqler.addSelect("t.detag");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeProdVersion main = service.findOne(id);
        String modna = jdbcDao.findOneString("select name from te_prod_model where id=?", main.getModid());
        main.setModna(modna);
        if (XstringUtil.isNotBlank(main.getPatid())) {
            main.setPatem(apService.findOne(main.getPatid()));
        }
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody TeProdVersion main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeProdVersion main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeProdVersionService service;

    @Autowired
    private TeAllotParamService apService;

    @Autowired
    private JdbcDao jdbcDao;

}
