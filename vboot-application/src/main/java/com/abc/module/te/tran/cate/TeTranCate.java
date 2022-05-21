package com.abc.module.te.tran.cate;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TeTranCate extends BaseMainEntity {

    private Integer ornum;

    private String notes;

}