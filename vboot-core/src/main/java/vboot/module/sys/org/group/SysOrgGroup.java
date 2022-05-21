package vboot.module.sys.org.group;

import vboot.module.sys.org.root.SysOrg;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class SysOrgGroup {

    @Id
    @Column(length = 32)
    private String id;//ID

    @Column(length = 100)
    private String name;//名称

    private String notes;//备注

    private Integer ornum;//排序号

    private Boolean avtag;//可用标记

    @Column(updatable = false)
    private Date crtim=new Date();//创建时间

    private Date uptim;//更新时间

    @ManyToMany
    @JoinTable(name = "sys_org_group_org", joinColumns = {@JoinColumn(name = "gid")},
            inverseJoinColumns = {@JoinColumn(name = "oid")})
    private List<SysOrg> members;



}
