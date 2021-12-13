package com.abc.sd.cust.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sd/cust/main")
public class SdCustMainApi {

    //分页查询项目清单
    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sd_cust_main");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes");
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    //查询单个项目
    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SdCustMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    //新增项目
    @PostMapping
    public RestResult post(@RequestBody SdCustMain main) {
        return RestResult.ok(service.insert(main));
    }

    //修改项目
    @PutMapping
    public RestResult put(@RequestBody SdCustMain main) {
        return RestResult.ok(service.update(main));
    }

    //删除项目
    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }


    //同时使用JPA与mybatis-plus的事务测试
    @PostMapping("test")
    public RestResult postTest() {
        service.test();
        return RestResult.ok();
    }


    @Autowired
    private SdCustMainService service;

    @Autowired
    private JdbcDao jdbcDao;

}
