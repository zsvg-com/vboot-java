package vboot.config.security.authz;


import vboot.common.mvc.api.RestResult;
import vboot.config.security.pojo.Zuser;
import vboot.module.sys.log.login.SysLogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthzController {


    @GetMapping("/getUserInfo")
    public RestResult getUserInfo(Authentication auth, HttpServletRequest request) {
        Zuser zuser = (Zuser) auth.getPrincipal();
        sysLogLoginService.save(zuser,request);
        return RestResult.ok("test");
    }

    @GetMapping("/getPermCode")
    public RestResult getPermCode(Authentication auth) {

        return RestResult.ok("test");
    }





    @GetMapping("/com/getZuser")
    public RestResult getZuser(Authentication auth) {
        return RestResult.ok("test");
    }

    @Autowired
    private SysLogLoginService sysLogLoginService;

    @Autowired
    private AuthzService authzService;

}
