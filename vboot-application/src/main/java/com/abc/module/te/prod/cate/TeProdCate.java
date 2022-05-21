package com.abc.module.te.prod.cate;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TeProdCate extends BaseMainEntity {

    private Integer ornum;

    private String notes;

}