package vboot.module.sys.log.login;

import cn.hutool.extra.servlet.ServletUtil;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XaddressUtil;
import vboot.config.security.pojo.Zuser;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class SysLogLoginService {

    @Autowired
    private SysLogLoginRepo repo;

    @Async
    public void save(Zuser zuser, HttpServletRequest request){

        SysLogLogin logLogin = new SysLogLogin();
        logLogin.setId(XstringUtil.getUUID());
        try {
            String agent = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            Browser browser = userAgent.getBrowser();

            OperatingSystem os = userAgent.getOperatingSystem();
            logLogin.setUsnam(zuser.getId());
            logLogin.setName(zuser.getName());
//            logLogin.setUip(XreqUtil.getIpAddress(request));
            String ip=ServletUtil.getClientIP(request);
            logLogin.setIp(ip);
            logLogin.setAddre(XaddressUtil.getRealAddressByIP(ip));
//            logLogin.setUsession(request.getSession().getId());

            logLogin.setAgdet(agent);
            logLogin.setAgbro(browser.toString());
            if("IE7".equals(browser.toString())){
                if (agent.contains("Trident/5")) {
                    logLogin.setAgbro("IE9C");
                }else if(agent.contains("Trident/6")){
                    logLogin.setAgbro("IE10C");
                }else if(agent.contains("Trident/7")){
                    logLogin.setAgbro("IE11C");
                }
            }
            logLogin.setAgeos(os.toString());
            repo.save(logLogin);
        }catch (Exception e){
            e.printStackTrace();
            log.error("???????????????????????????"+logLogin);
        }
    }

}
//
//          IE7
//        IE7 on Windows XP SP2
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)
//
//        IE7 on Windows 2003 Server
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2)
//
//        IE7 on Windows Vista
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)
//
//        ????????????
//        IE8
//        IE8 on Windows Vista
//        Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)
//
//        IE8 on Windows 7
//        Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0)
//
//        64-bit IE on 64-bit Windows:
//        Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Win64; x64; Trident/4.0)
//
//        32-bit IE on 64-bit Windows:
//        Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; WOW64; Trident/4.0)
//
//        WOW64 ?????? ???Windows on Windows 64-bit.???
//
//        ????????????
//        IE9
//        IE9 ??????UA string
//        Mozilla/5.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/5.0)
//
//        IE9 ???????????????UA string
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Trident/5.0)
//
//        ???IE8?????????IE9?????????????????????IE7????????????
//        ????????????
//        IE10
//        IE10 ??????UA string
//        Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; Trident/6.0)
//
//        IE10 ???????????????UA string
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/6.0)
//
//        ???IE8???IE9?????????IE10?????????????????????IE7????????????
//        ????????????
//        IE11
//        IE11 on Windows 8.1????????????
//        Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0) like Gecko
//
//        IE11 ???????????????UA string
//        Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; Trident/7.0; .NET4.0E; .NET4.0C)
//
//        ?????????aqhi
//        ?????????https://www.jianshu.com/p/ea72819b9eae
//        ???????????????
//        ??????????????????????????????????????????????????????????????????????????????????????????????????????
