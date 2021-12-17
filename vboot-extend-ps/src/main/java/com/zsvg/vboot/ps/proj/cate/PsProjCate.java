package com.zsvg.vboot.ps.proj.cate;

import com.zsvg.vboot.common.mvc.entity.BaseCateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PsProjCate extends BaseCateEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "pid")
    private PsProjCate parent;

    @Transient
    private List<PsProjCate> children = new ArrayList<>();

    private String notes;

}
