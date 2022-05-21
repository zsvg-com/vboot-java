package com.abc.module.te.allot.check;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeAllotCheck extends BaseMainEntity {

    private Integer ornum;

    private Integer venum;//版本号

    private String notes;

    @Column(length = 32)
    private String catid;//分类id

    @Column(length = 32)
    private String serid;//系列id

    @Column(length = 32)
    private String modid;//型号id

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "allid")
    private List<TeAllotCheckItem> items = new ArrayList<>();

}