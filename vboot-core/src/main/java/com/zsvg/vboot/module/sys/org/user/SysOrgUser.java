package com.zsvg.vboot.module.sys.org.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.zsvg.vboot.module.sys.org.root.SysOrg;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(indexes = {@Index(columnList = "usnam",unique = true),@Index(columnList = "tier",unique = true), @Index(columnList = "avtag")})
public class SysOrgUser {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String name;//名称

    @Column(length = 32)
    private String ninam;//昵称

    @Transient
    private String deptid;//部门ID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptid")
    private SysOrg dept;//部门

    @Column(length = 1000)
    private String tier;//层级

    @Column(length = 200)
    private String job;//职务

    @Column(length = 32)
    private String usnam;//登录名

    @Column(length = 64,updatable = false)
    @JsonIgnore
    private String pacod;//密码

    @Column(length = 64)
    private String email;//邮箱

    @Column(length = 32)
    private String monum;//手机号

    private Boolean watag = false;//提醒标记

    private String notes;//备注

    private Integer ornum;//排序号

    private Boolean avtag;//可用标记

    @Column(length = 1000)
    private String ldnam;//ldap 层级名称

    private Boolean retag = false;//准备标记

    @Column(length = 2000)
    private String conds; //组织架构集，用户ID，所有上级部门ID,岗位ID,群组ID

    @Column(length = 2000)
    private String perms; //后台所有权限集,用于验证URL权限

    @Column(length = 2000)
    private String menus; //前台菜单缓存

    @Column(length = 2000)
    private String btns; //前台按钮缓存


    public SysOrgUser() {

    }

    @Column(updatable = false)
    private Date crtim = new Date();//创建时间

    private Date uptim;//更新时间
}
