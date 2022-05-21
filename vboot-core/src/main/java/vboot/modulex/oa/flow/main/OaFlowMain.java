package vboot.modulex.oa.flow.main;

import vboot.common.mvc.entity.BaseMainEntity;
import vboot.module.bpm.proc.main.Zbpm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class OaFlowMain extends BaseMainEntity {

    @Column(length = 32)
    private String temid;//OA流程模板ID

    @Column(length = 32)
    private String protd;//全局流程模板ID

    private String notes;

    @Column(length = 8)
    private String state;

    @Transient
    private Zbpm zbpm;


}