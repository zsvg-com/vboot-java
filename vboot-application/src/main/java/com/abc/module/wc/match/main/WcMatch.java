package com.abc.module.wc.match.main;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WcMatch {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 128)
    private String name;//round_cn

    @Column(length = 32)
    private String homeId;

    private Integer homeScore;

    @Column(length = 32)
    private String awayId;

    private Integer awayScore;





//    @ManyToMany
//    @JoinTable(name = "wc_match_link_team", joinColumns = {@JoinColumn(name = "match_id")},
//            inverseJoinColumns = {@JoinColumn(name = "team_id")})
//    private List<WcTeamMain> teams;



}
