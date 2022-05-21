package vboot.modulex.oa.flow.rece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.web.XuserUtil;
import vboot.module.sys.org.rece.SysOrgRece;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("oa/flow/rece")
public class OaFlowReceApi {

    @GetMapping("list")
    public RestResult getList(Integer type) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String userId = XuserUtil.getUserId();
        Sqler userSqler = new Sqler("t.flowid as id", "oa_flow_rece");
        userSqler.addInnerJoin("u.name", "oa_flow_temp u", "u.id=t.flowid");
        userSqler.addInnerJoin("c.name as canam", "oa_flow_cate c", "c.id=u.catid");
        userSqler.addEqual("t.userid", userId);
        mapList.addAll(jdbcDao.findMapList(userSqler));
        return RestResult.ok(mapList);
    }

    @PostMapping
    public RestResult post(@RequestBody List<OaFlowRece> reces) throws SQLException {
        if (reces != null && reces.size() > 0) {
            service.update(reces);
        }
        return RestResult.ok();
    }


    @Autowired
    private OaFlowReceService service;

    @Autowired
    private JdbcDao jdbcDao;


}
