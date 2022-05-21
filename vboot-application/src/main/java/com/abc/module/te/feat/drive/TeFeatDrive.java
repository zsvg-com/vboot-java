package com.abc.module.te.feat.drive;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TeFeatDrive extends BaseMainEntity {

    @Column(length = 32)
    private String catid;//所属分类

    private Integer ornum;

    private String notes;

}