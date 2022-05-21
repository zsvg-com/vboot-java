package com.abc.module.sa.draw.main;

import vboot.common.mvc.entity.BaseMainEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SaDrawMain extends BaseMainEntity {

    @Column(length = 32)
    private String catid;//所属分类

    private Integer ornum;

    @ApiModelProperty("备注")
    private String notes;



}