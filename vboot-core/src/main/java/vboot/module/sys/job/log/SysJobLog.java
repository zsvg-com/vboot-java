package vboot.module.sys.job.log;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SysJobLog {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String sttim;

    @Column(length = 32)
    private String entim;

    @Column(length = 32)
    private String ret;

    @Column(length = 5000)
    private String msg;
}
