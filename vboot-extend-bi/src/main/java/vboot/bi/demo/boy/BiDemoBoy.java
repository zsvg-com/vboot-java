package vboot.bi.demo.boy;

import vboot.common.mvc.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BiDemoBoy extends BaseEntity
{
    private String notes;
}
