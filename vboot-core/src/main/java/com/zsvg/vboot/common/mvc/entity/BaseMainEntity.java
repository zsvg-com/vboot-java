package com.zsvg.vboot.common.mvc.entity;

import com.zsvg.vboot.module.sys.org.root.SysOrg;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseMainEntity {
    //----------------------id属性-----------------------
    @Id
    @Column(length = 32)
    protected String id;

    //----------------------权限属性-----------------------
//    @ManyToMany
//    @JoinTable(name = "xxx_viewer", joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "org")})
//    private List<SysOrg> viewers;//记录可查看者
//
//    @ManyToMany
//    @JoinTable(name = "xxx_editor", joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "org")})
//    private List<SysOrg> editors;//记录可编辑者


    //----------------------norm标准属性-----------------------
    @Column(length = 126)
    protected String name;//名称

    protected Boolean avtag=true;//可用标记 1启用，0禁用

    @ManyToOne
    @JoinColumn(name = "crman", updatable = false)
    protected SysOrg crman;//创建人

    @Column(updatable = false)
    protected Date crtim = new Date();//创建时间

    @ManyToOne
    @JoinColumn(name = "upman")
    protected SysOrg upman;//更新人

    protected Date uptim;//更新时间




}
