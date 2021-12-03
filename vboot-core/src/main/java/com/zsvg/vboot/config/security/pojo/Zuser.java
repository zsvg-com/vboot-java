package com.zsvg.vboot.config.security.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class Zuser implements UserDetails, Serializable {

    private static final long serialVersionUID = -2487878824333265058L;

    private String id;//用户ID

    private String name;//用户名称

    private Boolean watag;

    private String usnam;//用户名

    @JsonIgnore//前端不需要
    private long[] permArr;//权限集,用于验证URL权限 比较下哪个方式快1

    @JsonIgnore
    private List<String> permList;//权限集,用于验证URL权限 比较下哪个方式快2

    @JsonIgnore//前端不需要
    private String conds;//组织架构集，用户ID，所有上级部门ID,岗位ID,群组ID

    public Zuser(String id, String name, String usnam, long[] perms, String conds) {
        this.id = id;
        this.name = name;
        this.usnam = usnam;
        this.conds = conds;
        this.permArr = perms;
    }


    public Zuser() {

    }

    public Zuser(String id, String name, String usnam) {
        this.id = id;
        this.name = name;
        this.usnam = usnam;
    }

    //------------------------------
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


    public boolean hasPerm(int pos, long code) {
        if (isAdmin()) {
            return true;
        }
        if (permArr.length <= pos) {
            return false;
        }
        long ret = permArr[pos] & code;
        return !(ret == 0);
    }

    //----------------------------UserDetails------------------------------------------------
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.getUsnam();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    //@JSONField(serialize = false)
}
