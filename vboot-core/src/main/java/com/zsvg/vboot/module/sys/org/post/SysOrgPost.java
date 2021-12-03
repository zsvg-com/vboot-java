package com.zsvg.vboot.module.sys.org.post;

import com.zsvg.vboot.module.sys.org.root.SysOrg;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(columnList = "tier",unique = true), @Index(columnList = "avtag")})
public class SysOrgPost{
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String name;//名称

    @Transient
    private String deptid;//部门ID

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptid")
    private SysOrg dept;//部门

    @Column(length = 1000)
    private String tier;//层级

    private String notes;//备注

    private Integer ornum;//排序号

    private Boolean avtag;//可用标记


    @Column(updatable = false)
    private Date crtim = new Date();//创建时间

    private Date uptim;//更新时间

    @Column(length = 1000)
    private String ldnam;//ldap 层级名称

    @ManyToMany
    @JoinTable(name = "sys_org_post_user", joinColumns = {@JoinColumn(name = "pid")},
            inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<SysOrg> users;//员工列表


}
