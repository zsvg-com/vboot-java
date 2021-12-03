package com.zsvg.vboot.module.ass.num.main;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AssNumMain {
    @Id
    @Column(length = 8)
    private String id;

    @Column(length = 100)
    private String label;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String numod;

    @Column(length = 32)
    private String nupre;

    private Boolean nflag;

    @Column(length = 8)
    private String nunex;

    private Integer nulen;

    @Column(length = 8)
    private String cudat;
}
