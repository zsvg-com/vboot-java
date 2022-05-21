package vboot.module.sys.org.root;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SysOrg {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 100)
    private String name;

    public SysOrg(String id){
        this.id=id;
    }

    public SysOrg(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public SysOrg() {

    }
}
