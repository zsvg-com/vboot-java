package vboot.modulex.oa.flow.cate;

import vboot.common.mvc.entity.BaseCateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OaFlowCate extends BaseCateEntity {

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "pid")
    private OaFlowCate parent;//父类别

    @Transient
    private List<OaFlowCate> children = new ArrayList<>(); //行项目

    private String notes;//备注
}
