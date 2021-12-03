package com.zsvg.vboot.module.sys.auth.menu;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class SysAuthMenu {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String pid;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "pid")
//    @OrderBy("ornum ASC")

    @Transient
    private String pname; //行项目

    @Transient
    private List<SysAuthMenu> children = new ArrayList<>(); //行项目

    @Column(length = 32)
    private String type;//类型 C:目录，M：菜单，B：按钮

    @Column(length = 64)
    private String name;//名称

    @Column(length = 32)
    private String code;//代码

    private Integer ornum;//排序号

    @Column(length = 64)
    private String icon;//图标

    @Column(length = 64)
    private String path;//路由地址

    @Column(length = 64)
    private String comp;//组件路径

    @Column(length = 64)
    private String perm;//权限标识

    @Column(length = 64)
    private String redirect;//跳转

    private Boolean extag;//外链标记

    private Boolean catag;//缓存标记

    private Boolean shtag;//是否显示

    private Boolean avtag;//可用标记

    @Column(updatable = false)
    private Date crtim = new Date();//创建时间

    private Date uptim;//更新时间

    //--------以后扩展用---------
    @Column(length = 32)
    private String tcode;

    private Long lcode;

    private Integer ipos;

}