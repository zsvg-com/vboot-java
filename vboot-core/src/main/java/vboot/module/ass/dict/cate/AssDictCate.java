package vboot.module.ass.dict.cate;

import lombok.Getter;
import lombok.Setter;
import vboot.common.mvc.entity.BaseCateEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class AssDictCate extends BaseCateEntity {

    private String notes;//备注
}
