package com.zsvg.vboot.wf.tem.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class WfTemMain extends BaseMainEntity {

    @Column(length = 32)
    private String cateid;

    @Transient
    private String catename;

    private String notes;

    private Integer ornum;

    @Lob
    private String xml;




}