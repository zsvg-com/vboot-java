package com.zsvg.vboot.module.gen.org.rece;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.web.XuserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gen/org/rece")
public class GenOrgReceApi {

    @GetMapping
    public RestResult get(Integer type) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        String userId = XuserUtil.getUserId();
        if ((type & 8) != 0) {//用户
            Sqler userSqler = new Sqler("t.orgid as id", "gen_org_rece");
            userSqler.addInnerJoin("u.name", "sys_org_user u", "u.id=t.orgid");
            userSqler.addInnerJoin("d.name as dept", "sys_org_dept d", "d.id=u.deptid");
            userSqler.addEqual("t.userid", userId);
            System.out.println(userSqler.getSql());
            mapList.addAll(jdbcDao.findMapList(userSqler));
        }
        if ((type & 2) != 0) {//部门
            Sqler deptSqler = new Sqler("t.orgid as id", "gen_org_rece");
            deptSqler.addInnerJoin("o.name", "sys_org_dept o", "o.id=t.orgid");
            deptSqler.addEqual("t.userid", userId);
            mapList.addAll(jdbcDao.findMapList(deptSqler));
        }
        if ((type & 4) != 0) {//岗位
            Sqler postSqler = new Sqler("t.orgid as id", "gen_org_rece");
            postSqler.addInnerJoin("p.name", "sys_org_post p", "p.id=t.orgid");
            postSqler.addInnerJoin("d.name as dept", "sys_org_dept d", "d.id=p.deptid");
            postSqler.addEqual("t.userid", userId);
            mapList.addAll(jdbcDao.findMapList(postSqler));
        }
//        sqler.addDescOrder("t.uptim");
        return RestResult.ok(mapList);
    }

    @PostMapping
    public RestResult post(@RequestBody List<GenOrgRece> reces) {
        if(reces!=null&&reces.size()>0){
            service.update(reces);
        }
        return RestResult.ok();
    }


    @Autowired
    private GenOrgReceService service;

    @Autowired
    private JdbcDao jdbcDao;


}
