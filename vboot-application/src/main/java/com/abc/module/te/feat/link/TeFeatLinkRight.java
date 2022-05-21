package com.abc.module.te.feat.link;

import com.abc.module.te.feat.param.TeFeatParamOption;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class TeFeatLinkRight {

    @Id
    @Column(length = 32)
    @GeneratedValue(generator = "jpa-uuid")
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    private String id;

    private Integer ornum;

    @Column(length = 32)
    private String efpar;

    @Column(length = 32)
    private String efway;

    @Column(length = 32)
    private String efval;

    @ManyToMany
    @JoinTable(name = "te_feat_link_right_op", joinColumns = {@JoinColumn(name = "rid")},
            inverseJoinColumns = {@JoinColumn(name = "oid")})
    private List<TeFeatParamOption> efops;
}
