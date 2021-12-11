package com.zsvg.vboot.config.security.authn;

import cn.hutool.core.util.IdUtil;
import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.config.AppConfig;
import com.zsvg.vboot.config.dbx.redis.RedisHandler;
import com.zsvg.vboot.config.security.jwt.JwtHandler;
import com.zsvg.vboot.config.security.pojo.Duser;
import com.zsvg.vboot.config.security.pojo.Zmenu;
import com.zsvg.vboot.config.security.pojo.Zmeta;
import com.zsvg.vboot.config.security.pojo.Zuser;
import com.zsvg.vboot.module.sys.log.login.SysLogLoginService;
import com.zsvg.vboot.module.sys.org.user.SysOrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AuthnController {

    @PostMapping("/login")
    public RestResult login(@Valid @RequestBody LoginDto loginDTO, HttpServletRequest request) throws IOException, ClassNotFoundException {
//        System.out.println("开始登录");

        //1.登录验证
        Duser duser = null;
        try {
            duser = jdbcDao.getTp().queryForObject("select id,name,pacod,retag from sys_org_user where usnam=? and avtag=1",
                    new Object[]{loginDTO.getUsername()}, new BeanPropertyRowMapper<>(Duser.class));
        } catch (DataAccessException ignored) {

        }

        boolean passFlag = false;
        if (duser != null) {
            String encodePassword = DigestUtils.md5DigestAsHex(("abc" + loginDTO.getPassword() + "xyz").getBytes());//数据库密码验证
            if (encodePassword.equals(duser.getPacod())) {
                passFlag = true;
            } else {
                passFlag = ldapService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());//AD域密码验证
            }
        }
        if (!passFlag) {
            return RestResult.build(402, "账号不存在或密码错误");
        }

        //2.初始化用户信息，获取菜单与权限
        String atokenUUID = IdUtil.simpleUUID();
        String atoken = jwtHandler.createTokenByUuid(atokenUUID);
        String rtoken = jwtHandler.createTokenByUuid(loginDTO.getUsername());//需要加密下

        Map<String, Object> backMap = new HashMap<>();
        backMap.put("token", atoken);
        backMap.put("rtoken", rtoken);

        Zuser zuser = new Zuser(duser.getId(), duser.getName(), loginDTO.getUsername());
        authnService.initZuser(zuser, duser, backMap);//初始化用户

        redisHandler.set(atokenUUID, zuser, appConfig.getJwt().getAtime());//默认8小时过期

        //3.异步方式记录登录日志
//        sysLogLoginService.save(zuser,request);
        return RestResult.ok(backMap);

    }

    @GetMapping("/rtoken")
    public RestResult rtoken(@PathParam("token2") String token2) {
        System.out.println("token2=" + token2);
        String username = jwtHandler.getClaims(token2).getId();
        System.out.println("username=" + username);
        String uuid = IdUtil.simpleUUID();
        String token = jwtHandler.createTokenByUuid(uuid);
        Zuser zuser = authnService.getUser(username);
        redisHandler.set(uuid, zuser, 10000 / 1000);
        Map<String, Object> backMap = new HashMap<>();
        backMap.put("token", token);
        return RestResult.ok(backMap);
    }

    @GetMapping("/getMenuList")
    public RestResult getMenuList(Authentication auth) {
        List<Zmenu> zmenuList = new ArrayList<>();
        Zmenu zmenu = new Zmenu();
        zmenu.setPath("/sys/org");
        zmenu.setName("SysOrg");
        zmenu.setComponent("LAYOUT");
        zmenu.setRedirect("/sys/org/user");
        Zmeta zmeta = new Zmeta();
        zmeta.setOrderNo(1);
        zmeta.setIcon("ant-design:apartment-outlined");
        zmeta.setTitle("组织架构");
        zmenu.setMeta(zmeta);
        zmenuList.add(zmenu);

        Zmenu zmenu2 = new Zmenu();
        zmenu2.setPath("dept");
        zmenu2.setName("SysOrgDept");
        zmenu2.setComponent("/sys/org/dept/index.vue");
        Zmeta zmeta2 = new Zmeta();
        zmeta2.setOrderNo(1);
        zmeta2.setIcon("ant-design:apartment-outlined");
        zmeta2.setTitle("部门管理");
        zmeta2.setIgnoreKeepAlive(false);
        zmenu2.setMeta(zmeta2);

        zmenu.setChildren(new ArrayList<>());
        zmenu.getChildren().add(zmenu2);


//        return RestResult.ok(authnService.findMenuList(auth));
        return RestResult.ok(zmenuList);
    }


    @Autowired
    private AuthnService authnService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private JwtHandler jwtHandler;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private JdbcDao jdbcDao;

    @Autowired
    private SysLogLoginService sysLogLoginService;

    @Autowired
    private SysOrgUserService userService;


}
