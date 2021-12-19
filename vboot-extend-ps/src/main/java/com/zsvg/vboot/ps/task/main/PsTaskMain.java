package com.zsvg.vboot.ps.task.main;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.zsvg.vboot.common.mvc.entity.BaseMainEntity;
import com.zsvg.vboot.ps.task.list.PsTaskList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class PsTaskMain extends BaseMainEntity {

    @Transient
    private String listid;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "listid")
    @ExcelIgnore
    private PsTaskList list;

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;
}
