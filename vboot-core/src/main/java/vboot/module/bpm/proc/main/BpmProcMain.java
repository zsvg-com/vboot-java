package vboot.module.bpm.proc.main;

import vboot.common.mvc.entity.BaseMainEntity;
import vboot.module.sys.org.root.SysOrg;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BpmProcMain extends BaseMainEntity {


    //待用字段
    @Column(length = 32)
    private String modid;

    //待用字段
    private String modty;


    @Column(length = 32)
    private String temid;

    @Column(length = 8)
    private String state;

    public BpmProcMain() {

    }

    public BpmProcMain(Zbpm zbpm) {
        this.id=zbpm.getProid();
        this.name=zbpm.getProna();
        this.temid=zbpm.getTemid();
        this.crman=new SysOrg(zbpm.getHaman());
    }

}