package com.zsvg.vboot.ps.task.list;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import com.zsvg.vboot.ps.proj.cate.PsProjCate;
import com.zsvg.vboot.ps.proj.main.PsProjMain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PsTaskList extends BaseMainEntity {

    @Transient
    private String projid;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "projid")
    private PsProjMain proj;


}
