package vboot.module.sys.log.login;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class SysLogLogin {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String name;//用户名称

    @Column(length = 32)
    private String usnam;//登录账号
    @Column(length = 32)
    private String ip;//登录ip

    @Column(updatable = false)
    private Date crtim=new Date();

    @Column(length = 32)
    private String addre;//登录地点

    @Column(length = 64)
    private String usession;//取消了session!!!

    @Column(length = 32)
    private String ageos;//客户端操作系统

    @Column(length = 32)
    private String agbro;//客户端浏览器

    @Column(length = 512)
    private String agdet;//客户端详情




}
