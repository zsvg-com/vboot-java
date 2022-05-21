package vboot.modulex.oa.flow.main;

import vboot.common.mvc.api.PageData;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.Sqler;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vboot.module.bpm.task.main.BpmTaskMainService;

@RestController
@RequestMapping("oa/flow/main")
public class OaFlowMainApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqler = new Sqler("oa_flow_main");
        sqler.addInnerJoin("p.name temna","oa_flow_temp p", "p.id=t.temid");
        sqler.addLike("t.name", name);
        sqler.addSelect("t.state");
        PageData pageData = service.findPageData(sqler);
        taskMainService.findCurrentExmen(pageData.getItems());
        return RestResult.ok(pageData);
    }


    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        OaFlowMain main = service.findOne(id);
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody OaFlowMain main) throws DocumentException {
        service.insertx(main);
        return RestResult.ok();
    }

    @PutMapping
    public RestResult put(@RequestBody OaFlowMain main) {
        service.updatex(main);
        return RestResult.ok();
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private BpmTaskMainService taskMainService;

    @Autowired
    private OaFlowMainService service;

}
