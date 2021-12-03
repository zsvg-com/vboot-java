package com.zsvg.vboot.module.gen.org.rece;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(indexes = {@Index(columnList = "userid"),@Index(columnList = "orgid")})
public class GenOrgRece {

    @Id
    @Column(length = 32)
    private String id;//ID

    @Column(length = 32)
    private String userid;//用户ID

    @Column(length = 32)
    private String orgid;//最近使用的组织架构ID

    @Column
    private Date uptim = new Date();//最近使用时间
}
