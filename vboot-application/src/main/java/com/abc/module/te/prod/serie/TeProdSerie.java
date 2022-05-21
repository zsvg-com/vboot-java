package com.abc.module.te.prod.serie;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TeProdSerie extends BaseMainEntity {

    private Integer ornum;

    private String notes;

    @Column(length = 32)
    private String catid;




}