package com.zsvg.vboot.wf.tem.cate;

import com.zsvg.vboot.common.mvc.entity.BaseCateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class WfTemCate extends BaseCateEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "pid")
    private WfTemCate parent;//父类别

    private String notes;//备注
}
