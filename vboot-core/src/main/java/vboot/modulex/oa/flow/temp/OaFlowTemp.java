package vboot.modulex.oa.flow.temp;

import vboot.common.mvc.entity.BaseMainEntity;
import lombok.Getter;
import lombok.Setter;
import vboot.module.sys.org.root.SysOrg;
import vboot.modulex.oa.flow.cate.OaFlowCate;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OaFlowTemp extends BaseMainEntity {

    private Integer ornum;

    private String notes;

    private String protd;//全局流程模板ID

    @Lob
    private String vform;

    @Transient
    private String prxml;

    @ManyToOne
    @JoinColumn(name = "catid")
    private OaFlowCate cate;//流程分类

}