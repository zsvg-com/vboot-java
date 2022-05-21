package com.abc.module.te.feat.link;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeFeatLink extends BaseMainEntity {

    @Column(length = 32)
    private String catid;//所属分类

    @Column(length = 32)
    private String modid;//所属型号

    @Column(length = 32)
    private String trpar;//触发参数

    @Column(length = 32)
    private String trway;//触发方式

    private Integer trnum;//触发顺序

    private Integer trcon;//触发条件

    private Integer ornum;//排序号

    @Column(length = 32)
    private String liway;//联动方式

    @Column(length = 2000)
    private String lifun;//联动函数

    private String notes;


//    @Column(length = 64)
//    private String pacod;
//
//    @Column(length = 64)
//    private String panam;
//
//    @Column(length = 64)
//    private String pbcod;
//
//    @Column(length = 64)
//    private String pbnam;
//
//    @Column(length = 64)
//    private String pccod;
//
//    @Column(length = 64)
//    private String pcnam;
//
//    @Column(length = 64)
//    private String pdcod;
//
//    @Column(length = 64)
//    private String pdnam;
//
//    @Column(length = 64)
//    private String pxcod;
//
//    @Column(length = 64)
//    private String pxnam;
//
//    @Column(length = 64)
//    private String pycod;
//
//    @Column(length = 64)
//    private String pynam;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "linid")
    @OrderBy("ornum ASC")
    private List<TeFeatLinkItem> items = new ArrayList<>();

}