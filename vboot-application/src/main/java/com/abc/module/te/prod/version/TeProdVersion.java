package com.abc.module.te.prod.version;

import com.abc.module.te.allot.check.TeAllotCheck;
import com.abc.module.te.allot.drive.TeAllotDrive;
import com.abc.module.te.allot.param.TeAllotParam;
import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TeProdVersion extends BaseMainEntity {

    private Integer ornum;//排序号

    private Integer venum;//版本号

    private Boolean detag;//默认版本标记

    @Column(length = 32)
    private String stdat;//生效开始时间

    @Column(length = 32)
    private String endat;//生效结束时间

    @Column(length = 32)
    private String coway;//配置方式

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

    @Column(length = 32)
    private String modid;//型号ID

    @Transient
    private String modna;//型号名称

}