package vboot.common.util.web;

import vboot.module.sys.org.root.SysOrg;
import vboot.config.security.pojo.Zuser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class XuserUtil {

    public static String[] admins;

    public static boolean isAdmin(String usnam) {
        if ("sa".equals(usnam)) {
            return true;
        }
        for (int i = 0; i < XuserUtil.admins.length; i++) {
            if (XuserUtil.admins[i].equals(usnam)) {
                return true;
            }
        }
        return false;
    }

    public static SysOrg getUser() {
        String userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userId = calcUserId(userId, authentication);
        return new SysOrg(userId, "");
    }

    public static String getUserId() {
        String userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userId = calcUserId(userId, authentication);
        return userId;
    }

    private static String calcUserId(String userId, Authentication authentication) {
//        if(authentication==null){
//            return "sa";
//        }
        if (authentication.getPrincipal() instanceof String) {
            userId = (String) authentication.getPrincipal();
        } else if (authentication.getPrincipal() instanceof Zuser) {
            Zuser zuser = (Zuser) authentication.getPrincipal();
            userId = zuser.getId();
        }
        return userId;
    }

}
