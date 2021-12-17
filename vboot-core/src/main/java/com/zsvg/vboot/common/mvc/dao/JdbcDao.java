package com.zsvg.vboot.common.mvc.dao;

import com.zsvg.vboot.module.sys.org.root.SysOrg;
import com.zsvg.vboot.common.mvc.api.PageData;
import com.zsvg.vboot.common.mvc.pojo.ZidName;
import com.zsvg.vboot.common.mvc.pojo.Zinp;
import com.zsvg.vboot.common.mvc.pojo.Ztree;
import com.zsvg.vboot.common.util.lang.XjsonUtil;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.common.util.web.XreqUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JdbcDao {

    @Value("${app.db.type}")
    private String DB_TYPE;

    public PageData findPageData(Sqler sqler) {
        XreqUtil.setPageParam(sqler);
        Integer count = jdbcTemplate.queryForObject(sqler.getSizeSql(), sqler.getParams(), Integer.class);
        if (count == null || count == 0) {
            return new PageData(0, new ArrayList<>());
        }
        List<Map<String, Object>> list;
        if ("mysql".equals(DB_TYPE)) {
            list = jdbcTemplate.queryForList(sqler.getMysqlPagingSql(), sqler.getParams());
        }else if ("oracle".equals(DB_TYPE)) {
            list = jdbcTemplate.queryForList(sqler.getOraclePagingLowerCaseSql(), sqler.getParams());
        }  else {
            System.err.println("目前只支持mysql与oracle");
            list = new ArrayList<>();
        }
        return new PageData(count, list);
    }

    //--------------------------------------------------------------------------------

    public List<Ztree> findTreeList(Sqler sqler) {
        List<Ztree> list = jdbcTemplate.query(sqler.getSql(), sqler.getParams(), (rs, rowNum) -> {
            Ztree ztree = new Ztree();
            ztree.setId(rs.getString("id"));
            ztree.setName(rs.getString("name"));
            ztree.setPid(rs.getString("pid"));
            return ztree;
        });
        return XjsonUtil.buildByRecursive(list);
    }


    public List<ZidName> findIdNameList(String sql, Object... args) {
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
            ZidName ztwo = new ZidName();
            ztwo.setId(rs.getString("id"));
            ztwo.setName(rs.getString("name"));
            return ztwo;
        });
    }

    public List<ZidName> getIdNameListByTable(String table, String name) {
        String sql = "select id,name from " + table + " where avtag = 1";
        if (XstringUtil.isBlank(name)) {
            return findIdNameList(sql);
        } else {
            return jdbcTemplate.query(sql + " and name like ?", new Object[]{"%" + name + "%"}, (rs, rowNum) -> {
                ZidName ztwo = new ZidName();
                ztwo.setId(rs.getString("id"));
                ztwo.setName(rs.getString("name"));
                return ztwo;
            });
        }
    }


    public List<ZidName> findIdNameList(Sqler sqlHelper) {
        return findIdNameList((sqlHelper.getSql()), sqlHelper.getParams());
    }


    public List<SysOrg> findOrgList(String sql, Object... args) {
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
            SysOrg sysOrg = new SysOrg();
            sysOrg.setId(rs.getString("id"));
            sysOrg.setName(rs.getString("name"));
            return sysOrg;
        });
    }

    public List<Zinp> findInpList(String sql, Object... args) {
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> {
            Zinp zinp = new Zinp();
            zinp.setId(rs.getString("id"));
            zinp.setName(rs.getString("name"));
            zinp.setPid(rs.getString("pid"));
            return zinp;
        });
    }

    public List<Zinp> findInpList(Sqler sqlHelper) {
        return findInpList(sqlHelper.getSql(), sqlHelper.getParams());
    }

    public List<Zinp> findInpListByTable(String table) {
        String sql = "select id,name,pid from " + table + " where avtag = 1";
        return findInpList(sql);
    }

    public List<Zinp> findInpListByTable(String table, String name) {
        String sql = "select id,name,pid from " + table + " where avtag = 1";
        if (XstringUtil.isBlank(name)) {
            return findInpList(sql);
        } else {
            return jdbcTemplate.query(sql + " and name like ?", new Object[]{"%" + name + "%"}, (rs, rowNum) -> new Zinp(rs.getString("id"), rs.getString("name"), rs.getString("pid")));
        }
    }

    public List<String> findStringList(String sql, Object... args) {
        return jdbcTemplate.query(sql, args, (rs, rowNum) -> rs.getString("id"));
    }

    public List<String> findStringList(Sqler sqler) {
        return jdbcTemplate.query(sqler.getSql(), sqler.getParams(), (rs, rowNum) -> rs.getString("id"));
    }

    public Integer findSize(Sqler sqler) {
        return jdbcTemplate.queryForObject(sqler.getSizeSql(), sqler.getParams(), Integer.class);
    }

    public String findOneString(String sql, Object... args) {
        String str = null;
        try {
            str = jdbcTemplate.queryForObject(sql, args, String.class);
        } catch (Exception ignored) {

        }
        return str;
    }

    public String findOneString(Sqler sqler) {
        return findOneString(sqler.getSql(), sqler.getParams());
    }

    public Map<String, Object> findMap(Sqler sqler) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqler.getSql(), sqler.getParams());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
//        return jdbcTemplate.queryForMap(sqler.getSql(), sqler.getParams());
    }

    public Map<String, Object> findMap(String sql, Object... args) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        return list.get(0);
    }

    public List<Map<String, Object>> findMapList(Sqler sqler) {
        return jdbcTemplate.queryForList(sqler.getSql(), sqler.getParams());
    }

    public List<Map<String, Object>> findMapList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }


    public String findOneName(String table, String id) {
        if (XstringUtil.isBlank(id)) {
            return null;
        } else {
            return jdbcTemplate.queryForObject("select name from " + table + " where id = ?", new Object[]{id}, String.class);
        }
    }


    //queryForObject 如果返回没结果会报错


    //增删改----------------------------------------------------------------------------------
    public int update(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    public int update(BaseSqler sqler) throws SQLException {
        return jdbcTemplate.update(sqler.getSql(), sqler.getParams());
    }

    public int[] batch(String sql, List<java.lang.Object[]> list) {
        return jdbcTemplate.batchUpdate(sql, list);
    }

    public String getDbType() {
        return DB_TYPE;
    }

    public JdbcTemplate getTp() {
//        jdbcDao.getTp().query(sql, new Object[]{}, new BeanPropertyRowMapper<>(Qmarg.class));
        return jdbcTemplate;
    }

    @Resource
    private JdbcTemplate jdbcTemplate;
}
