package vboot.module.bpm.proc.param;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BpmProcParam {


    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 64)
    private String pakey;

    @Column(length = 512)
    private String paval;

    @Column(length = 32)
    private String offty;

    @Column(length = 32)
    private String offid;

    @Column(length = 32)
    private String proid;




}
