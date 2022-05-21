package com.abc.module.te.feat.param;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.ZidName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("te/feat/param")
public class TeFeatParamApi {

    @GetMapping
    public RestResult get(String name,String scrid) {
        Sqler sqler = new Sqler("te_feat_param");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.scrid", scrid);
        sqler.addLeftJoin("s.name as scrna", "te_feat_screen s", "s.id=t.scrid");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeFeatParam main = service.findOne(id);
        String sql = "select name from te_feat_screen where id=?";
        String scrna = jdbcDao.findOneString(sql, main.getScrid());
        main.setScrna(scrna);
        return RestResult.ok(main);
    }

    @GetMapping("options/{id}")
    public RestResult getOptions(@PathVariable String id) {
        String sql = "select id,name,code from te_feat_param_option where parid=?";
        List<Map<String, Object>> mapList = jdbcDao.findMapList(sql, id);
        return RestResult.ok(mapList);
    }

    @GetMapping("list")
    public RestResult getListByCate(String catid) {
        String sql = "select t.id,t.name from te_feat_param t inner join te_feat_screen s on s.id=t.scrid " +
                "inner join te_prod_cate c on c.id=s.catid where s.catid=?";
        List<ZidName> idNameList = jdbcDao.findIdNameList(sql, catid);
        return RestResult.ok(idNameList);
    }

    @GetMapping("mallot")
    public RestResult mallot(String id) {
        return RestResult.ok(service.findData(id));
    }

    @PostMapping
    public RestResult post(@RequestBody TeFeatParam main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeFeatParam main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeFeatParamService service;

    @Autowired
    private JdbcDao jdbcDao;
}
