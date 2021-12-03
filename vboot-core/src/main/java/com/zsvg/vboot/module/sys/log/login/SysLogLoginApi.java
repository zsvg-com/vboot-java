package com.zsvg.vboot.module.sys.log.login;

import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sys/log/login")
public class SysLogLoginApi {

    @GetMapping
    public RestResult get(String name) {
        Sqler sqlHelper = new Sqler("t.id,t.uname,t.crtim,t.uip,t.agent_os as agentOs,t.agent_browser as agentBrowser,t.agent_info as agentInfo",
                "sys_log_login");
        sqlHelper.addLike("t.uname", name);
        sqlHelper.addOrder("t.crtim desc");
        return RestResult.ok(jdbcDao.findPageData(sqlHelper));
    }

    @DeleteMapping("{ids}")
    public RestResult delete(@PathVariable String[] ids)  {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return RestResult.ok();
    }


    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysLogLoginRepo repo;
}
