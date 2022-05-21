package vboot.module.sys.org.dept;

import vboot.module.sys.org.root.SysOrg;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(columnList = "tier",unique = true), @Index(columnList = "avtag")})
public class SysOrgDept {

    @Id
    @Column(length = 32)
    private String id;//ID

    @Column(length = 100)
    private String name;//名称

    @Transient
    private List<SysOrgDept> children = new ArrayList<>(); //行项目

    @Transient
    private String pid;//父ID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private SysOrg parent;//父元素

    private Integer type;//1为机构,2为部门,4为岗位,8为用户,16为常用群组,32为角色线

    @Column(length = 1000)
    private String tier;//层级

    @Column(length = 32)
    private String label;//标签,主要用于区分组织架构分类，ABC为系统内置组织架构

    private String notes;//备注

    private Integer ornum;//排序号

    private Boolean avtag;//可用标记

    @Column(length = 1000)
    private String ldnam;//ldap 层级名称


    @Column(updatable = false)
    private Date crtim = new Date();//创建时间


    private Date uptim;//更新时间


//    @Column(length = 32)
//    private String tecod;// 销售组代码

//    @Transient
//    private String cid;//公司ID

//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name = "cid")
//    private SysOrg corp;//公司


}
