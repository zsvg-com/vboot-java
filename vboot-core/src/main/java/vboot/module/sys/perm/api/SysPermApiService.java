package vboot.module.sys.perm.api;

import cn.hutool.core.lang.ClassScanner;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.pojo.Zinp;
import vboot.common.util.lang.XmapUtil;
import vboot.common.util.web.XuserUtil;
import vboot.config.security.pojo.Zuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermApiService {
    private List<Yperm> g1perms;
    private List<Yperm> g2perms;
    private List<Yperm> sperms;
    private List<Yperm> uperms;
    private List<Yperm> dperms;
    private List<Yperm> pperms;

    public void initPerm() {
        List<Zinp> javaList = getScanList();
//        javaList.addAll(XmapUtil.getRootList());
        List<String> ordList = jdbcDao.findStringList("select id from sys_perm_api");

        //收集最大权限位及最大权限位的最大权限码
        String sql = "select max(u.ipos) as ipos,max(u.lcode) as lcode from sys_perm_api u where u.ipos = (select max(uu.ipos) from sys_perm_api uu)";
        Map<String, Object> maxMap = jdbcDao.findMap(sql);
        int pos = 0;
        long code = 0;
        if (maxMap != null && maxMap.get("ipos") != null) {
            pos = Integer.parseInt(String.valueOf(maxMap.get("ipos")));
            code = Long.parseLong(String.valueOf(maxMap.get("lcode")));
        }
        //比较两个list得到insertList
        List<Object[]> insertList = new ArrayList<Object[]>();
        List<Object[]> updateList = new ArrayList<Object[]>();
        for (Zinp zinp : javaList) {
            boolean flag = false;
            for (String ordstr : ordList) {
                if (zinp.getId().equals(ordstr)) {
                    flag = true;
                    break;
                }
            }
            //插入
            if (!flag) {
                if (!zinp.getId().startsWith("~")) {
                    Object[] objects = new Object[6];
                    objects[0] = zinp.getId();
                    objects[1] = zinp.getPid();
                    objects[2] = 0;
                    objects[3] = 0;
                    objects[4] = zinp.getName();
                    objects[5] = 0;
                    insertList.add(objects);
                } else {
                    if (code >= (1L << 62)) {
                        pos = pos + 1;
                        code = 1;
                    } else {
                        if (code == 0) {
                            code = 1;
                        } else {
                            code = code << 1;
                        }
                    }
                    Object[] objects = new Object[6];
                    objects[0] = zinp.getId();
                    objects[1] = zinp.getPid();
                    objects[2] = pos;
                    objects[3] = code;
                    objects[4] = zinp.getName();
                    objects[5] = 0;
                    insertList.add(objects);
                }
            } else {//更新
                Object[] objects = new Object[3];
                objects[0] = zinp.getName();
                objects[1] = zinp.getPid();
                objects[2] = zinp.getId();
                updateList.add(objects);
            }
        }
        //设置是否为公共资源
        for (Object[] objects : insertList) {
            if (objects[0].toString().contains("_c")) {
                objects[5] = 1;
            }
        }
        //更新数据库
        String initSql = "UPDATE sys_perm_api SET avtag=0";
        String updateSql = "UPDATE sys_perm_api SET name=?,pid=?,avtag=1 WHERE id=?";
        String insertSql = "INSERT INTO sys_perm_api(id,pid,ipos,lcode,name,cotag,avtag) VALUES(?,?,?,?,?,?,1)";
        jdbcDao.update(initSql);
        jdbcDao.batch(updateSql, updateList);
        jdbcDao.batch(insertSql, insertList);
        AUTHPOS = pos;
        //初始化所有用户权限
        String updateUserSql = "update sys_org_user set retag=0";
        jdbcDao.update(updateUserSql);
        //将所有要求认证的权限放入application
        String g1permSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag= 1 and cotag = 0 and lcode<>0 and id like '~G%' and id not like '%}'";
        this.g1perms = getPerms(g1permSql);

        String g2permSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag = 1 and cotag = 0 and lcode<>0 and id like '~G%' and id like '%}'";
        this.g2perms = getPerms(g2permSql);

        String spermSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag = 1 and cotag = 0 and lcode<>0 and id like '~PO%'";
        this.sperms = getPerms(spermSql);

        String upermSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag = 1 and cotag = 0 and lcode<>0 and id like '~PU%'";
        this.uperms = getPerms(upermSql);

        String dpermSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag = 1 and cotag = 0 and lcode<>0 and id like '~D%'";
        this.dperms = getPerms(dpermSql);

        String ppermSql = "SELECT id,ipos,lcode,tcode from sys_perm_api where avtag = 1 and cotag = 0 and lcode<>0 and id like '~PA%'";
        this.pperms = getPerms(ppermSql);

        initAdmin();

    }

    public void initAdmin(){
        //admin用户
        String saSql="select u.usnam as id from sys_perm_label_org o inner join sys_org_user u on u.id=o.org inner join sys_perm_label l on l.id=o.id where l.code='admin'";
        List<String> saList = jdbcDao.findStringList(saSql);
        XuserUtil.admins = saList.toArray(new String[0]);
    }

    private List<Yperm> getPerms(String sql){
        return  jdbcDao.getTp().query(sql, (rs, rowNum) -> {
            Yperm yperm = new Yperm();
            String id=rs.getString("id").split(":")[1];
            if(id.endsWith("}")){
                yperm.setId(id.substring(0,id.lastIndexOf("/")));
            }
            else{
                yperm.setId(id);
            }
            yperm.setPos(rs.getInt("ipos"));
            yperm.setCode(rs.getLong("lcode"));
            yperm.setTcode(rs.getString("tcode"));
            return yperm;
        });
    }


    @Autowired
    private JdbcDao jdbcDao;

    public static Integer AUTHPOS = 0;

    private List<Zinp> getScanList() {
        List<Zinp> list = new ArrayList<Zinp>();
        Set<Class<?>> classes =  ClassScanner.scanPackage("vboot");

        for (Class<?> clazz : classes) {
            RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                String url = requestMapping.value()[0];
                //---> /n 开头的不需要登录， /co 只要登录就有权限  /sy 需要管理员才有权限  其余通过rbac权限机制判断
                if (!url.startsWith("n")&&!url.startsWith("co")&&!url.startsWith("sy")) {
                    Zinp topInp = new Zinp();
                    topInp.setId(url);
                    if(!"".equals(requestMapping.name())){
                        topInp.setName(url+" "+requestMapping.name());
                    }else{
                        topInp.setName(XmapUtil.getName(url));
                    }
                    String[] arr=url.split("/");
                    String s = "";
                    for (int i = 0; i <arr.length-1 ; i++) {
                        s += arr[i] + "/";
                    }
                    if(arr.length>=1&&s.length()>0){

                        s=s.substring(0,s.length()-1);
                    }
                    if(!url.contains("/")&&url.length()==4){
                        topInp.setPid(url.substring(0,3));
                    }else{
                        topInp.setPid(s);
                    }
                    list.add(topInp);
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        GetMapping getMapping = method.getAnnotation(GetMapping.class);
                        if (getMapping != null) {
                            Zinp zinp = new Zinp();
                            String id = "~GET:/" + url;
                            if (getMapping.value().length > 0) {
                                id = "~GET:/" + url + "/" + getMapping.value()[0];
                            }
                            zinp.setId(id);
                            zinp.setPid(url);
                            if("".equals(getMapping.name())){
                                zinp.setName(id);
                            }else{
                                zinp.setName(getMapping.name());
                            }
//                          zinp.setName(XmapUtil.getName("/" + url + "/" + methodMapping.value()[0]));
                            if(!id.endsWith("_c")&&!id.endsWith("_n")){
                                list.add(zinp);
                            }
                            continue;
                        }
                        PostMapping postMapping = method.getAnnotation(PostMapping.class);
                        if (postMapping != null) {
                            Zinp zinp = new Zinp();
                            String id = "~POS:/" + url;
                            if (postMapping.value().length > 0) {
                                id = "~POS:/" + url + "/" + postMapping.value()[0];
                            }
                            zinp.setId(id);
                            zinp.setPid(url);
                            if("".equals(postMapping.name())){
                                zinp.setName(id);
                            }else{
                                zinp.setName(postMapping.name());
                            }
                            if(!id.endsWith("_c")&&!id.endsWith("_n")){
                                list.add(zinp);
                            }
                            continue;
                        }
                        PutMapping putMapping = method.getAnnotation(PutMapping.class);
                        if (putMapping != null) {
                            Zinp zinp = new Zinp();
                            String id = "~PUT:/" + url;
                            if (putMapping.value().length > 0) {
                                id = "~PUT:/" + url + "/" + putMapping.value()[0];
                            }
                            zinp.setId(id);
                            zinp.setPid(url);
                            if("".equals(putMapping.name())){
                                zinp.setName(id);
                            }else{
                                zinp.setName(putMapping.name());
                            }
                            if(!id.endsWith("_c")&&!id.endsWith("_n")){
                                list.add(zinp);
                            }
                            continue;
                        }
                        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                        if (deleteMapping != null) {
                            Zinp zinp = new Zinp();
                            String id = "~DEL:/" + url;
                            if (deleteMapping.value().length > 0) {
                                id = "~DEL:/" + url + "/" + deleteMapping.value()[0];
                            }
                            zinp.setId(id);
                            zinp.setPid(url);
                            if("".equals(deleteMapping.name())){
                                zinp.setName(id);
                            }else{
                                zinp.setName(deleteMapping.name());
                            }
                            if(!id.endsWith("_c")&&!id.endsWith("_n")){
                                list.add(zinp);
                            }
                        }

                        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
                        if (patchMapping != null) {
                            Zinp zinp = new Zinp();
                            String id = "~PAT:/" + url;
                            if (patchMapping.value().length > 0) {
                                id = "~PAT:/" + url + "/" + patchMapping.value()[0];
                            }
                            zinp.setId(id);
                            zinp.setPid(url);
                            if("".equals(patchMapping.name())){
                                zinp.setName(id);
                            }else{
                                zinp.setName(patchMapping.name());
                            }
                            if(!id.endsWith("_c")&&!id.endsWith("_n")){
                                list.add(zinp);
                            }
                        }

                    }
                }
            }
        }
        return list;
    }

    public boolean hasPerm(Zuser zuser, String url, String type){
        if(zuser.isAdmin()){
            return true;
        }
        boolean flag=false;
        if("pat".equals(type)){
            for (int i = 0; i < pperms.size(); i++) {
                if(pperms.get(i).getId().equals(url)){
                    flag = zuser.hasPerm(pperms.get(i).getPos(), pperms.get(i).getCode());
                    break;
                }
            }
        }else if("get".equals(type)){
            for (int i = 0; i < g1perms.size(); i++) {
                if(g1perms.get(i).getId().equals(url)){
                    flag = zuser.hasPerm(g1perms.get(i).getPos(), g1perms.get(i).getCode());
                    break;
                }
            }
        }
        return flag;
    }
    public boolean hasPerm(Zuser zuser, String url){
        return hasPerm(zuser, url, "pat");
    }


    //get and set---------


    public List<Yperm> getG1perms() {
        return g1perms;
    }

    public List<Yperm> getG2perms() {
        return g2perms;
    }

    public List<Yperm> getSperms() {
        return sperms;
    }

    public List<Yperm> getUperms() {
        return uperms;
    }

    public List<Yperm> getDperms() {
        return dperms;
    }
}
