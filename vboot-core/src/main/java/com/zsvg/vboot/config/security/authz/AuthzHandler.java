package com.zsvg.vboot.config.security.authz;

import com.zsvg.vboot.config.security.pojo.Zuser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthzHandler {

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String uri=request.getRequestURI();

        try {
            Zuser zuser = (Zuser) authentication.getPrincipal();
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }


        return true;
    }

}
