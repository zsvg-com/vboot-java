package vboot.common.util.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class XspringUtil  {

    private static ApplicationContext applicationContext = null;

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(XspringUtil.applicationContext == null){
            XspringUtil.applicationContext  = applicationContext;
        }
    }

    public static ApplicationContext getApp() {
        return applicationContext;
    }

    public static Object getBean(String name){
            return getApp().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return getApp().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApp().getBean(name, clazz);
    }

}