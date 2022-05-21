package com.abc.module.oa.doc.main;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class OaDocMain extends BaseMainEntity {

    private String notes;


}