package vboot.module.sys.perm.role;

import lombok.Getter;
import lombok.Setter;
import vboot.common.mvc.entity.BaseMainEntity;
import vboot.module.sys.org.root.SysOrg;
import vboot.module.sys.perm.menu.SysPermMenu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SysPermRole extends BaseMainEntity
{

    @Column(length = 32)
    private String type;

    @Column(length = 32)
    private String label;

    private String notes;

    //拥有的菜单
    @ManyToMany
    @JoinTable(name = "sys_perm_role_menu",
            joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "mid")})
    private List<SysPermMenu> menus  = new ArrayList<>();

    //授权的用户
    @ManyToMany
    @JoinTable(name = "sys_perm_role_org",
            joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "oid")})
    private List<SysOrg> orgs = new ArrayList<>();

}
