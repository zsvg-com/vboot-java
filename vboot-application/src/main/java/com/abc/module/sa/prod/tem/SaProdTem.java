package com.abc.module.sa.prod.tem;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SaProdTem extends BaseMainEntity {

    private Integer ornum;

    private String notes;

    @Column(length = 32)
    private String modid;




}