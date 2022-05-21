package com.abc.module.sa.agent.main;

import com.abc.module.te.feat.param.TeFeatParamOption;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vboot.common.mvc.entity.BaseMainEntity;
import vboot.module.sys.org.root.SysOrg;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class SaAgentMain extends BaseMainEntity {

    private String addre;

    @ApiModelProperty("备注")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "opman")
    private SysOrg opman;//创建人

    @ManyToMany
    @JoinTable(name = "sa_agent_main_viewer", joinColumns = {@JoinColumn(name = "mid")},
            inverseJoinColumns = {@JoinColumn(name = "oid")})
    private List<SysOrg> viewers;
}