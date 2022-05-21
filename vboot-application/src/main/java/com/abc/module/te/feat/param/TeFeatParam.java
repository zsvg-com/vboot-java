package com.abc.module.te.feat.param;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeFeatParam extends BaseMainEntity {

    private Boolean drtag;//土建参数标记

    private Boolean qutag;//询价参数标记

    @Column(length = 32)
    private String shmod;//显示模式

    @Column(length = 32)
    private String code;

    private Integer ornum;

    private String notes;

    @Column(length = 32)
    private String scrid;

    @Transient
    private String scrna;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parid")
    @OrderBy("ornum ASC")
    private List<TeFeatParamOption> options = new ArrayList<>();

    @Column(length = 2000)
    private String asfun;//关联函数



}