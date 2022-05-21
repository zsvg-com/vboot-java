package com.abc.module.te.prod.model;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TeProdModel extends BaseMainEntity {

    private Integer ornum;

    private String notes;

    @Column(length = 32)
    private String catid;

    @Column(length = 32)
    private String serid;


}