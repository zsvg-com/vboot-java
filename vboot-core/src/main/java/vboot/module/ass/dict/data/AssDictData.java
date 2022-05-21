package vboot.module.ass.dict.data;

import lombok.Getter;
import lombok.Setter;
import vboot.common.mvc.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AssDictData extends BaseEntity {

    private String notes;//备注

    @Column(length = 32)
    private String code;//编码

    private Integer ornum;


}