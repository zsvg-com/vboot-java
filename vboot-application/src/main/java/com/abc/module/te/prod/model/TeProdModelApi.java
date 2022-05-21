package com.abc.module.te.prod.model;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("te/prod/model")
public class TeProdModelApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("te_prod_model");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        sqler.addLeftJoin("c.name as catna", "te_prod_cate c", "c.id=t.catid");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        TeProdModel main = service.findOne(id);
        return RestResult.ok(main);
    }

    @GetMapping("list")
    public RestResult getList(String serid,String catid) {
        if(XstringUtil.isNotBlank(catid)){
            String sql = "select m.id,m.name from te_prod_model m where m.catid=?";
            List<Map<String, Object>> mapList = jdbcDao.findMapList(sql, catid);
            return RestResult.ok(mapList);
        }else if(XstringUtil.isNotBlank(serid)){
            String sql = "select m.id,m.name from te_prod_model m where m.serid=?";
            List<Map<String, Object>> mapList = jdbcDao.findMapList(sql, serid);
            return RestResult.ok(mapList);
        }else{
            return null;
        }
    }

    @GetMapping("zmodel/{id}")
    public RestResult getFeat(@PathVariable String id) {
        Zmodel zmodel = service.getZmodel(id);
        return RestResult.ok(zmodel);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        List<Zinp> list = service.findTreeList(name);
        return RestResult.ok(list);
    }

    @PostMapping
    public RestResult post(@RequestBody TeProdModel main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody TeProdModel main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private TeProdModelService service;

    @Autowired
    private JdbcDao jdbcDao;

}
