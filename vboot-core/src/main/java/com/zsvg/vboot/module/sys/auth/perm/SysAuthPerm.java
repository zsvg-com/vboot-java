package com.zsvg.vboot.module.sys.auth.perm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SysAuthPerm {

    @Id
    @Column(length = 64)
    private String id;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String pid;

    private Long lcode;

    private Integer ipos;

    private Boolean cotag;//公共标记

    @Column(length = 32)
    private String tcode;

    private Boolean avtag;//可用标记

}
