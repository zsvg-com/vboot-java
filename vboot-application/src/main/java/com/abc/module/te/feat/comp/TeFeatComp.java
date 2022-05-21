package com.abc.module.te.feat.comp;

import com.abc.module.te.allot.check.TeAllotCheck;
import com.abc.module.te.allot.drive.TeAllotDrive;
import com.abc.module.te.allot.param.TeAllotParam;
import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class TeFeatComp extends BaseMainEntity {

    private Integer ornum;//排序号

    @Column(length = 32)
    private String paway;//参数分配方式

    @Column(length = 32)
    private String patid;//参数分配模板id

    @Transient
    private TeAllotParam patem;//参数分配模板引用

    @Column(length = 32)
    private String laway;//联动分配方式

//    @Column(length = 32)
//    private String latid;//联动分配模板id

//    @Transient
//    private TeAllotParam latem;//联动分配模板引用

    @Column(length = 32)
    private String caway;//校验分配方式

    @Column(length = 32)
    private String catid;//校验分配模板id

    @Transient
    private TeAllotCheck catem;//校验分配模板引用

    @Column(length = 32)
    private String daway;//驱动分配方式

    @Column(length = 32)
    private String datid;//驱动分配模板id

    @Transient
    private TeAllotDrive datem;//驱动分配模板引用

    private String notes;

}