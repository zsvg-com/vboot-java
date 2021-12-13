package com.abc.sd.cust.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class SdCustMain extends BaseMainEntity {

    //备注
    private String notes;


}