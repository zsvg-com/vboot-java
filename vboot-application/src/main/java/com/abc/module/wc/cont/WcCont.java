package com.abc.module.wc.cont;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class WcCont {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 64)
    private String name;



}
