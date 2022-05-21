package com.abc.module.sa.proj.main;

import com.abc.module.sa.prod.group.SaProdGroup;
import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SaProjMain extends BaseMainEntity {

    //备注
    private String notes;

    //项目地址
    private String addre;

    @Transient
    private List<SaProdGroup> groups = new ArrayList<>(); //产品组

}