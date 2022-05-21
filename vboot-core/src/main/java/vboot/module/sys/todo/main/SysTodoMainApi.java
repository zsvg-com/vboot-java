package vboot.module.sys.todo.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/todo/main")
public class SysTodoMainApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sys_todo_main");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.crtim desc");
        sqler.addSelect("t.link");
        sqler.addInnerJoin("","sys_todo_user t2", "t2.tid=t.id");
        sqler.addInnerJoin("o.name as exman","sys_org o", "o.id=t2.uid");
        return RestResult.ok(jdbcDao.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysTodoMain main = service.findOne(id);
        return RestResult.ok(main);
    }


    @PostMapping
    public RestResult post(@RequestBody SysTodoMain main) throws DocumentException {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysTodoMain main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SysTodoMainService service;

    @Autowired
    private JdbcDao jdbcDao;

}
