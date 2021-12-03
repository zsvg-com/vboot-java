package com.zsvg.vboot.module.gen.sys.auth;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.config.security.pojo.Zuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gen/auth")
public class GenAuthApi {

    @PostMapping(value="btns")
    public RestResult getBtns(HttpServletRequest request, @RequestBody Map<String,String> btns) {
        Zuser zuser = null;
        Map<String, Boolean> backMap = new HashMap<>();
        for (String str : btns.keySet()) {
            if(zuser.isAdmin()){
                backMap.put(str, true);
                continue;
            }
            backMap.put(str, false);
            String sql = "select ipos,lcode from sys_auth_perm p where p.id = ?";
            try{
                Map<String, Object> map = jdbcDao.findMap(sql, btns.get(str));
                if (zuser.hasPerm(Integer.parseInt(""+map.get("ipos")) , Long.parseLong(""+map.get("lcode")))) {
                    backMap.put(str, true);
                }
            }catch (Exception e){

            }
        }
        return RestResult.ok(backMap);
    }

    //获取用户信息，如果没有登录到不了这里
    @GetMapping("user")
    public RestResult getUser(HttpServletRequest request,String id)  {
        Map<String, Object> map = new HashMap<>();
        Zuser zuser= null;
        if("sa".equals(id)){
            //主页滚动通知
            String noteSql = "select n.notes from sys_por_note n inner join sys_por_note_org o on o.id=n.id where n.label='sa' and n.avtag=1 and o.org in (" + zuser.getConds() + ")";
            String note = jdbcDao.findOneString(noteSql);
            map.put("note", note);

            //主页弹框通知
            String note2Sql = "select n.id,r.org from sys_por_note n inner join sys_por_note_org o on o.id=n.id left join sys_por_note_reader r on r.id=n.id AND r.org = '"+zuser.getId()+"' where n.label='sa2' and n.avtag=1 and o.org in (" + zuser.getConds() + ")";
            List<Map<String, Object>> note2List = jdbcDao.findMapList(note2Sql);
            if(note2List.size()>0){
                if(note2List.get(0).get("org")==null){
                    map.put("note2", note2List.get(0).get("id"));
                }
            }


        }
        map.put("zuser", zuser);
        return RestResult.build(200, "已登录",map);
    }


    @Autowired
    private JdbcDao jdbcDao;
}
