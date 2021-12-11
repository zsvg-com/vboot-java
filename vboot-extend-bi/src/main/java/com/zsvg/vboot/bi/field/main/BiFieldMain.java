package com.zsvg.vboot.bi.field.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BiFieldMain extends BaseMainEntity {

    @Column(length = 64)
    private String comet;

    @Column(length = 64)
    private String notes;

    @Column(length = 32)
    private String tableid;
}
