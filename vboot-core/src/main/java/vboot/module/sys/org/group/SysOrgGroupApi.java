package vboot.module.sys.org.group;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/org/group")
public class SysOrgGroupApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("sys_org_group");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.ornum,t.notes,t.crtim,t.uptim");
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        SysOrgGroup main = service.findById(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody SysOrgGroup main) {
        return RestResult.ok(service.insert(main));
    }

    @PutMapping
    public RestResult put(@RequestBody SysOrgGroup main) {
        return RestResult.ok(service.update(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private SysOrgGroupService service;

}
