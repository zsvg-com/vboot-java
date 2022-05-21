package com.abc.module.te.feat.link;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TeFeatLinkLeft {

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;//ID

    private Integer ornum;//排序号

    @Column(length = 32)
    private String copar;

    @Column(length = 32)
    private String coway;

    @Column(length = 32)
    private String coval;

}
