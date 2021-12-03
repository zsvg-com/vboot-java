package com.zsvg.vboot.config.security.authz;


import com.zsvg.vboot.common.mvc.api.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthzController {


    @GetMapping("/getUserInfo")
    public RestResult getUserInfo(Authentication auth) {

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
    private AuthzService authzService;

}
