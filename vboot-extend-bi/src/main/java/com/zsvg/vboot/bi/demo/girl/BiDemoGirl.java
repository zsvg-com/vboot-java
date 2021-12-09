package com.zsvg.vboot.bi.demo.girl;

import com.zsvg.vboot.common.mvc.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BiDemoGirl extends BaseEntity
{
    private String notes;
}
