package vboot.bi.conn.main;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BiConnMain extends BaseMainEntity {

    @Column(length = 64)
    private String comet;//注释

    @Column(length = 64)
    private String notes;//备注

}