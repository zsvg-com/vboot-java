package vboot.module.ass.dict.main;

import lombok.Getter;
import lombok.Setter;
import vboot.common.mvc.entity.BaseMainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AssDictMain extends BaseMainEntity {

    private String notes;

    @Column(length = 32)
    private String code;//编码

    private Integer ornum;

}