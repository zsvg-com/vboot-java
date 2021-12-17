package com.zsvg.vboot.ps.task.main;

import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import com.zsvg.vboot.ps.proj.main.PsProjMain;
import com.zsvg.vboot.ps.task.list.PsTaskList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PsTaskMain extends BaseMainEntity {

    @Transient
    private String listid;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "listid")
    private PsTaskList list;


}
