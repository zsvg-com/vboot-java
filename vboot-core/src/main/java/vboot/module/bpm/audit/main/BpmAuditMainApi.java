package vboot.module.bpm.audit.main;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bpm/audit/main")
public class BpmAuditMainApi {

    @GetMapping("list/{proid}")
    public RestResult list(@PathVariable String proid) {
        String sql = "select t.id,t.crtim,t.facna,t.facno,t.opnot,t.opinf,o.name as haman from bpm_audit_main t " +
                "inner join sys_org o on o.id=t.haman " +
                "where t.proid=? order by t.crtim";
        return RestResult.ok(jdbcDao.findMapList(sql,proid));
    }


    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private BpmAuditMainService service;

}
