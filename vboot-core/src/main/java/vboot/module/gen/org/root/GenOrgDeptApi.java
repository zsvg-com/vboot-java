package vboot.module.gen.org.root;

import vboot.common.mvc.api.RestResult;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.pojo.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gen/org/dept")
public class GenOrgDeptApi {

    //查询部门树
    @GetMapping("tree")
    public RestResult getTree(String name) {
        Sqler sqler = new Sqler("sys_org_dept");
        sqler.addSelect("t.pid");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.ornum");
        sqler.addWhere("t.avtag=1");
        List<Ztree> list = jdbcDao.findTreeList(sqler);
        return RestResult.ok(list);
    }

    //查询部门树
    @GetMapping("list")
    public RestResult getList(String name) {
        Sqler sqler = new Sqler("sys_org_dept");
        sqler.addLike("t.name", name);
        sqler.addOrder("t.ornum");
        sqler.addWhere("t.avtag=1");
        sqler.addSelect("t.pid");
        return RestResult.ok(jdbcDao.findMapList(sqler));
    }


    @Autowired
    private JdbcDao jdbcDao;


//    @Autowired
//    private SysOrgDeptService deptService;

}
