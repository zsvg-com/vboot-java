package vboot.module.sys.job.main;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Entity
public class SysJobMain
{
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 128, updatable = false)
    private String name;

    @Column(length = 128, updatable = false)
    private String code;

//    @Column(length = 32, updatable = false)
    @Transient
    private String jid;
//
//    @Column(length = 32, updatable = false)
    @Transient
    private String jgroup;

    private Integer retyp;

    @Column(length = 32, updatable = false)
    private String reurl;

    private Boolean avtag;

    private Integer ornum;

    @Column(updatable = false)
    private Date crtim = new Date();

    @Column(length = 32)
    private String cron;

    private String notes;

}
