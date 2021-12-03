package com.zsvg.vboot.module.sys.job.main;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class SysJobMain
{
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 128, updatable = false)
    private String name;

    @Column(length = 32, updatable = false)
    private String jid;

    @Column(length = 32, updatable = false)
    private String jgroup;

    @Column(length = 32)
    private String state;

    @Column(length = 32)
    private String cron;

}
