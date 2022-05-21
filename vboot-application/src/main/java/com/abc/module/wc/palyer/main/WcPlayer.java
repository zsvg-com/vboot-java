package com.abc.module.wc.palyer.main;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class WcPlayer {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 64)
    private String name;//中文名称

    @Column(length = 64)
    private String enName;//英文名称

    private String enFullName;//英文全称

    private Date birthday;//出生日期

    private Integer height;//身高（cm）

    private Integer weight;//体重（kg）

    private String position;//场上位置

    private String shirtNumber;//球衣号

    private String teamId;//球队ID

    private String club;//俱乐部

    private BigDecimal weekly;//周薪  可以改成 salary

    private BigDecimal adrate;//每年广告费

    private String idolId;//偶像的ID

}
