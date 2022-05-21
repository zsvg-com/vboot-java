package com.abc;

import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XspringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableCaching
@SpringBootApplication
@ComponentScan({"vboot","com.abc"})
@EnableJpaRepositories({"vboot","com.abc"})
@Slf4j
@EnableAsync
//@EnableSwagger2
public class Application {

    public static void main(String[] args) {

        ApplicationContext app =  SpringApplication.run(Application.class, args);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for (int i = 0; i < beanDefinitionNames.length; i++) {
//            System.out.println(beanDefinitionNames[i]);
        }
        XspringUtil.setApplicationContext(app);
        Environment env = app.getEnvironment();
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (XstringUtil.isBlank(path)) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + path +"/vue.html"+ "\n\t" +
                "External访问网址: \thttp://" + ip + ":" + port + path +"/vue.html"+ "\n\t");

    }

}

