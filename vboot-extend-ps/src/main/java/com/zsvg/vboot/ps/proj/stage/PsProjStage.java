package com.zsvg.vboot.ps.proj.stage;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PsProjStage extends BaseMainEntity {

//    @Transient
//    private String projid;
//
//    @ManyToOne(fetch= FetchType.EAGER)
//    @JoinColumn(name = "projid")
//    private PsProjMain proj;

    @Column(length = 32)
    private String projid;

    @Transient
    private String projname;

    private Integer ornum;


}
