package com.abc.module.wc.stat;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class WcStat {

    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String palyerId;//球员ID

    @Column(length = 32)
    private String matchId;//比赛ID

    private Integer totalScoringAtt;//射门次数

    private Integer ontargetScoringAtt;//射正次数

    private Integer goals;//进球数

    private Integer goalAssist;//助攻

    private Integer minsPlayed;//出场时间

    private Integer fouls;//犯规

    private Integer wasFouled;//被犯规

    private Integer totalPass;//总传球数

    private Integer accuratePass;//成功传球数

    private Integer saves;//扑救数

    private Boolean status;//是否首发

    private Boolean isHome;//是否为主队

}
