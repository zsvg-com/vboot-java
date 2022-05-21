package com.abc.module.wc.team.main;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WcTeam {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String name;

//    @Column(length = 64)
//    private String enName;

    @Column(length = 32)
    private String coach;//主教练

    @Column(length = 32)
    private String contId;//大洲的ID


}
