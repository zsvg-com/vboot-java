package com.zsvg.vboot.ps.proj.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import com.zsvg.vboot.ps.proj.cate.PsProjCate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PsProjMain extends BaseMainEntity {

    @Transient
    private String cid;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "cid")
    private PsProjCate cate;

    private String notes;

    private Integer ornum;
}
