package com.abc.module.sa.prod.group;

import com.abc.module.sa.prod.main.SaProdMain;
import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SaProdGroup extends BaseMainEntity {




    //备注
    private String notes;

    @Column(length = 32)
    private String projid;//项目

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "groid")
    @OrderBy("ornum ASC")
    private List<SaProdMain> prods = new ArrayList<>();




}