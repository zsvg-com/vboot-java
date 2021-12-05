package com.abc.sd.proj.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SdProjMain extends BaseMainEntity {

    //备注
    private String notes;

    //项目地址
    private String addre;

}