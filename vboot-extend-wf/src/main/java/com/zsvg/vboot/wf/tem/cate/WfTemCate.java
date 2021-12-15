package com.zsvg.vboot.wf.tem.cate;

import com.zsvg.vboot.common.mvc.entity.BaseCateEntity;
import com.zsvg.vboot.module.sys.auth.menu.SysAuthMenu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class WfTemCate extends BaseCateEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "pid")
    private WfTemCate parent;//父类别

    @Transient
    private List<WfTemCate> children = new ArrayList<>(); //行项目

    private String notes;//备注
}
