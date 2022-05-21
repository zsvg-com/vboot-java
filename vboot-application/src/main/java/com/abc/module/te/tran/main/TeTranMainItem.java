package com.abc.module.te.tran.main;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class TeTranMainItem {

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;//ID

    private Integer ornum;//排序号

    @Column(length = 100)
    private String paval;

    @Column(length = 100)
    private String pbval;

    @Column(length = 100)
    private String pcval;

    @Column(length = 100)
    private String pdval;

    @Column(length = 100)
    private String pxval;

    @Column(length = 100)
    private String pyval;

    private String notes;//备注

}