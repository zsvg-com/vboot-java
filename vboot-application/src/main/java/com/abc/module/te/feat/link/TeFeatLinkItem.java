package com.abc.module.te.feat.link;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TeFeatLinkItem {

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;//ID

    private Integer ornum;//排序号

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "iteid")
    @OrderBy("ornum ASC")
    private List<TeFeatLinkLeft> lefts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "iteid")
    @OrderBy("ornum ASC")
    private List<TeFeatLinkRight> rights = new ArrayList<>();

    private String notes;//备注

}