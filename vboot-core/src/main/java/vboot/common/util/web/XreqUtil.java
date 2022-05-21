package vboot.common.util.web;

import cn.hutool.core.convert.Convert;
import vboot.common.mvc.dao.Sqler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class XreqUtil {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "page";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";


    public static void testParams(HttpServletRequest request) {
        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            Object pValue = request.getParameter(pName);
            System.out.println(pName + ":" + pValue);
        }
    }



    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static void setPageParam(Sqler sqler) {
        HttpServletRequest request = getRequest();
        Integer pageNum =  Convert.toInt(request.getParameter(PAGE_NUM));
        Integer pageSize =  Convert.toInt(request.getParameter(PAGE_SIZE));
        if(pageNum!=null){
            sqler.setPanum(pageNum);
        }
        if(pageSize!=null){
            sqler.setPasiz(pageSize);
        }
    }


    /**
     * 获取String参数
     */
    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue)
    {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name)
    {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue)
    {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name)
    {
        return Convert.toBool(getRequest().getParameter(name));
    }

    /**
     * 获取Boolean参数
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue)
    {
        return Convert.toBool(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
//    public static boolean isAjaxRequest(HttpServletRequest request)
//    {
//        String accept = request.getHeader("accept");
//        if (accept != null && accept.indexOf("application/json") != -1)
//        {
//            return true;
//        }
//
//        String xRequestedWith = request.getHeader("X-Requested-With");
//        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
//        {
//            return true;
//        }
//
//        String uri = request.getRequestURI();
//        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml"))
//        {
//            return true;
//        }
//
//        String ajax = request.getParameter("__ajax");
//        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml"))
//        {
//            return true;
//        }
//        return false;
//    }

}