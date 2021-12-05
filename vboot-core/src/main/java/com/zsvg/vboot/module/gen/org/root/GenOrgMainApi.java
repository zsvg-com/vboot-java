package com.zsvg.vboot.module.gen.org.root;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gen/org/main")
public class GenOrgMainApi {

    //根据部门ID，查询下级所有的部门,岗位,用户
    @GetMapping()
    public RestResult get(String deptid, Integer type, String name) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        if(XstringUtil.isBlank(name)&&XstringUtil.isBlank(deptid)){
            return RestResult.ok(mapList);
        }
        if ((type & 2) != 0) {//部门
            Sqler deptSqler = new Sqler("sys_org_dept");
            if(XstringUtil.isNotBlank(name)){
                deptSqler.addLike("t.name", name);
            }else{
                deptSqler.addEqual("t.pid", deptid);
            }
            mapList.addAll(jdbcDao.findMapList(deptSqler));
        }
        if ((type & 4) != 0) {//岗位
            Sqler postSqler = new Sqler("sys_org_post");
            if(XstringUtil.isNotBlank(name)){
                postSqler.addLike("t.name", name);
            }else{
                postSqler.addEqual("t.deptid", deptid);
            }
            mapList.addAll(jdbcDao.findMapList(postSqler));
        }
        if ((type & 8) != 0) {//用户
            Sqler userSqler = new Sqler("sys_org_user");
            if(XstringUtil.isNotBlank(name)){
                userSqler.addLike("t.name", name);
            }else{
                userSqler.addEqual("t.deptid", deptid);
            }
            mapList.addAll(jdbcDao.findMapList(userSqler));
        }
        return RestResult.ok(mapList);
    }

    @Autowired
    private JdbcDao jdbcDao;


}
