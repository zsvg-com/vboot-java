package com.zsvg.vboot.wf.ins.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class WfInsMain extends BaseMainEntity {

    @Column(length = 32)
    private String temid;

    @Transient
    private String temna;

    private String notes;

}