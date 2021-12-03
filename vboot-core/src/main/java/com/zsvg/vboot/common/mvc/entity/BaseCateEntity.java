package com.zsvg.vboot.common.mvc.entity;

import com.zsvg.vboot.module.sys.org.root.SysOrg;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseCateEntity {
    //----------------------id属性-----------------------
     @Id
     @Column(length = 32)
     protected String id;
    //----------------------权限属性-----------------------
//    @ManyToMany
//    @JoinTable(name = "xxx_viewer", joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "org")})
//    private List<SysOrg> viewers;//分类可查看使用者,拥有此权限者可以创建此分类下主数据信息
//
//    @ManyToMany
//        @JoinTable(name = "xxx_editor", joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "org")})
//    private List<SysOrg> editors;//分类可编辑维护者,拥有此权限者可以查看此分类下所有主数据信息

    //----------------------层级属性参考-----------------------
    @Column(length = 1000)
    protected String tier;//层级信息

    @Transient
    private String pid;

    @Transient
    protected BaseCateEntity parent;//父类别
    //----------------------norm标准属性-----------------------
    @Column(length = 100)
    protected String name;//名称

    @Column(length = 64)
    protected String label;//标签

    @Column(length = 500)
    protected String notes;//备注

    protected Boolean avtag;//可用标记 1启用，0禁用

    protected Integer ornum;//排序号

    @ManyToOne
    @JoinColumn(name = "crman", updatable = false)
    protected SysOrg crman;//创建人

    @Column(updatable = false)
    protected Date crtim = new Date();//创建时间

    protected Date uptim;//更新时间

    @ManyToOne
    @JoinColumn(name = "upman")
    protected SysOrg upman;//更新人

}
