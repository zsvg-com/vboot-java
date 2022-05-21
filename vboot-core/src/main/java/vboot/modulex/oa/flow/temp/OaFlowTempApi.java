package vboot.modulex.oa.flow.temp;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Zinp;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vboot.common.util.lang.XstringUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("oa/flow/temp")
public class OaFlowTempApi {

    @GetMapping
    public RestResult get(String name,String catid) {
        Sqler sqler = new Sqler("oa_flow_temp");
        sqler.addInnerJoin("c.name catna", "oa_flow_cate c", "c.id=t.catid");
        if(XstringUtil.isNotBlank(name)){
            sqler.addLike("t.name", name);
        }else if(XstringUtil.isNotBlank(catid)){
            sqler.addEqual("t.catid", catid);
        }
        return RestResult.ok(service.findPageData(sqler));
    }

    @GetMapping("tree")
    public RestResult getTree() {
        List<Zinp> list = service.findTreeList();
        return RestResult.ok(list);
    }

    @GetMapping("list")
    public RestResult getList(String catid, String name) {
        Sqler sqler = new Sqler("oa_flow_temp");
        sqler.addInnerJoin("c.name catna", "oa_flow_cate c", "c.id=t.catid");
        sqler.addLike("t.name", name);
        sqler.addEqual("t.catid", catid);
        sqler.addOrder("t.ornum");
        return RestResult.ok(jdbcDao.findMapList(sqler));
    }

    @GetMapping("one/{id}")
    public RestResult getOne(@PathVariable String id) {
        OaFlowTemp main = service.findOne(id);
        String sql = "select orxml from bpm_proc_temp where id=?";
        Map<String, Object> map = jdbcDao.findMap(sql, main.getProtd());
        main.setPrxml("" + map.get("orxml"));
        return RestResult.ok(main);
    }

    @PostMapping
    public RestResult post(@RequestBody OaFlowTemp main) throws DocumentException {
        return RestResult.ok(service.insertx(main));
    }

    @PutMapping
    public RestResult put(@RequestBody OaFlowTemp main) {
        return RestResult.ok(service.updatex(main));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids) {
        return RestResult.ok(service.delete(ids));
    }

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private OaFlowTempService service;

}
