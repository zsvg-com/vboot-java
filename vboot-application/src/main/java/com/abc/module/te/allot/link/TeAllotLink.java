package com.abc.module.te.allot.link;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeAllotLink extends BaseMainEntity {

    private Integer ornum;

    private Integer venum;//版本号

    private String notes;

    @Column(length = 32)
    private String catid;

    @Column(length = 32)
    private String serid;//系列id

    @Column(length = 32)
    private String modid;//型号id

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "allid")
    private List<TeAllotLinkItem> items = new ArrayList<>();

}