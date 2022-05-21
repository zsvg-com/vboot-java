package com.abc.module.te.prod.test;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TeProdTest extends BaseMainEntity {

    private String notes;

}