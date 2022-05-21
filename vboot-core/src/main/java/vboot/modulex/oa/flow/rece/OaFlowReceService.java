package vboot.modulex.oa.flow.rece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vboot.common.mvc.api.PageData;
import vboot.common.mvc.dao.Dsqler;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XuserUtil;
import vboot.module.sys.org.rece.SysOrgRece;
import vboot.module.sys.org.rece.SysOrgReceRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class OaFlowReceService {

    public int update(List<OaFlowRece> reces) throws SQLException {
        String userid = XuserUtil.getUserId();
        String flowids = "(";
        for (OaFlowRece rece : reces) {
            rece.setFlowid(rece.getId());
            rece.setId(XstringUtil.getUUID());
            rece.setUserid(userid);
            flowids += "'" + rece.getFlowid() + "',";
        }
        flowids = flowids.substring(0, flowids.length() - 1) + ")";

        //数据库删除本次已传的记录
        Dsqler dsqler = new Dsqler("oa_flow_rece");
        dsqler.addWhere("userid=?", userid);
        dsqler.addWhere("flowid in " + flowids);
        System.out.println(dsqler.getSql());
        jdbcDao.update(dsqler);

        //删除当前数据库最近10次前的数据
        Sqler sqler = new Sqler("t.id", "oa_flow_rece");
        sqler.setPanum(2);
        sqler.setPasiz(10);
        sqler.addWhere("t.userid=?", userid);
        sqler.addDescOrder("t.uptim");
        List<Map<String, Object>> pageMapList = jdbcDao.findPageMapList(sqler);
        String ids = "(";
        if (pageMapList != null && pageMapList.size() > 0) {
            for (Map<String, Object> map : pageMapList) {
                ids += "'" + map.get("id") + "',";
            }
            Dsqler dsqler2 = new Dsqler("oa_flow_rece");
            dsqler2.addWhere("id in " + ids);
            jdbcDao.update(dsqler2);
        }

        //插入本次使用的记录
        repo.saveAll(reces);
        return reces.size();
    }


    @Autowired
    private JdbcDao jdbcDao;


    @Autowired
    private OaFlowReceRepo repo;


}
