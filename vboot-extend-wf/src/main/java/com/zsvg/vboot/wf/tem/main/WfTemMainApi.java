package com.zsvg.vboot.wf.tem.main;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("wf/tem/main")
public class WfTemMainApi {

    private String table = "wf_tem_main";

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler(table);
        sqler.addLike("t.name", name);
        sqler.addSelect("t.notes,t.ornum");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("list")
    public RestResult getList(String name,String cateid) {
        Sqler sqler = new Sqler(table);
        sqler.addSelect("t.notes,t.ornum");
        if(XstringUtil.isBlank(name)&&XstringUtil.isBlank(cateid)){
            return RestResult.empty();
        }else if("all".equals(name)){
            return RestResult.ok(jdbcDao.findMapList(sqler));
        }else if(!XstringUtil.isBlank(cateid)){
            sqler.addEqual("t.cateid", cateid);
            return RestResult.ok(jdbcDao.findMapList(sqler));
        }else{
            sqler.addLike("t.name", name);
            System.out.println(sqler.getSql());
            return RestResult.ok(jdbcDao.findMapList(sqler));
        }
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        WfTemMain main = service.findOne(id);
        if(main.getCateid()!=null){
            Sqler sqler = new Sqler("name","wf_tem_cate");
            sqler.addEqual("id",main.getCateid());
            String name = jdbcDao.findOneString(sqler);
            main.setCatename(name);
        }
        return RestResult.ok(main);
    }

    @GetMapping("tree")
    public RestResult getTree(String name) {
        return RestResult.ok(service.findTree(name));
    }


    @PostMapping
    public RestResult post(@RequestBody WfTemMain main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody WfTemMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private WfTemMainService service;

}
