package com.zsvg.vboot.wf.tem.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class WfTemMain extends BaseMainEntity {

    private String notes;

    @Column(length = 32)
    private String code;//编码

    private Integer ornum;

}