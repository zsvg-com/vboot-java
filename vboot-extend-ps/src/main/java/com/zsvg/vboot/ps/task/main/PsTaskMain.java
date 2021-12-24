package com.zsvg.vboot.ps.task.main;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import com.zsvg.vboot.ps.proj.cate.PsProjCate;
import com.zsvg.vboot.ps.proj.stage.PsProjStage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class PsTaskMain extends BaseMainEntity {

    @Column(length = 32)
    private String stageid;

    @Transient
    private String stagename;

    @Transient
    private String pid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private PsTaskMain parent;

    @Transient
    private List<PsTaskMain> children = new ArrayList<>();

    private Integer ornum;

    @ExcelProperty("字符串标题")
    private String string2;

    @ExcelProperty("日期标题")
    private Date date2;

    @ExcelProperty("数字标题")
    private Double doubleData;
}
