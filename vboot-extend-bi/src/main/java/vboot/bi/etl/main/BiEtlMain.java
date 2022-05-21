package vboot.bi.etl.main;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BiEtlMain extends BaseMainEntity
{
    @Column(length = 32)
    private String code;

    @Column(length = 32)
    private String cron;

    private String notes;

    @Column(length = 128)
    private String zpath;

    @Column(length = 128)
    private String zname;
}
