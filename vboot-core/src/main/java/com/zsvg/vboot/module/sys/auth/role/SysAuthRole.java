package com.zsvg.vboot.module.sys.auth.role;

import com.zsvg.vboot.module.sys.org.root.SysOrg;
import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;
import com.zsvg.vboot.module.sys.auth.menu.SysAuthMenu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SysAuthRole extends BaseMainEntity
{

    @Column(length = 32)
    private String type;

    @Column(length = 32)
    private String label;

    private String notes;

    //拥有的菜单
    @ManyToMany
    @JoinTable(name = "sys_auth_role_menu",
            joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "mid")})
    private List<SysAuthMenu> menus  = new ArrayList<>();

//    //拥有的权限
//    @ManyToMany
//    @JoinTable(name = "sys_auth_role_perm",
//            joinColumns = {@JoinColumn(name = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "pid")})
//    private List<SysAuthPerm> perms = new ArrayList<>();

    //授权的用户
    @ManyToMany
    @JoinTable(name = "sys_auth_role_org",
            joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "oid")})
    private List<SysOrg> orgs = new ArrayList<>();

}
