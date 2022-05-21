package com.abc.module.te.tran.main;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeTranMain extends BaseMainEntity {

    @Column(length = 64)
    private String catid;

    private Integer ornum;

    private String notes;

    @Column(length = 64)
    private String pacod;

    @Column(length = 64)
    private String panam;

    @Column(length = 64)
    private String pbcod;

    @Column(length = 64)
    private String pbnam;

    @Column(length = 64)
    private String pccod;

    @Column(length = 64)
    private String pcnam;

    @Column(length = 64)
    private String pdcod;

    @Column(length = 64)
    private String pdnam;

    @Column(length = 64)
    private String pxcod;

    @Column(length = 64)
    private String pxnam;

    @Column(length = 64)
    private String pycod;

    @Column(length = 64)
    private String pynam;

    @Column(length = 32)
    private String trway;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mainid")
    @OrderBy("ornum ASC")
    private List<TeTranMainItem> items = new ArrayList<>();

}