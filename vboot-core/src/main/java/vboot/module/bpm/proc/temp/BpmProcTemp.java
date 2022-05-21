package vboot.module.bpm.proc.temp;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
public class BpmProcTemp extends BaseMainEntity {

    public BpmProcTemp() {

    }

    public BpmProcTemp(BaseMainEntity main) {
        this.name=main.getName();
        this.crman=main.getCrman();
        this.crtim=main.getCrtim();
    }

    @Lob
    private String orxml;//原始XML

    @Lob
    private String chxml;//变动后的XML

}