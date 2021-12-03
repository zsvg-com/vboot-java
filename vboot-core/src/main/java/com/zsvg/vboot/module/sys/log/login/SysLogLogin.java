package com.zsvg.vboot.module.sys.log.login;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SysLogLogin {
    @Id
    @Column(length = 32)
    private String id;

    @Column(updatable = false)
    private Date crtim=new Date();

    @Column(length = 32)
    private String usnam;//用户名

    @Column(length = 32)
    private String uname;//用户名称

    @Column(length = 32)
    private String uip;//登录ip

    @Column(length = 64)
    private String usession;//

    @Column(length = 512)
    private String agentInfo;//客户端详情

    @Column(length = 32)
    private String agentOs;//客户端操作系统

    @Column(length = 32)
    private String agentBrowser;//客户端浏览器




}
