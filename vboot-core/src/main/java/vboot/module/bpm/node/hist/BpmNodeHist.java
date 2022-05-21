package vboot.module.bpm.node.hist;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BpmNodeHist {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String facno;//当前节点ID:N1,N2

    @Column(length = 126)
    private String facna;//当前节点名称

    @Column(length = 32)
    private String facty;//当前节点类型

    @Column(length = 32)
    private String tarno;//目标节点ID

    @Column(length = 126)
    private String tarna;//目标节点名称

    @Column(length = 32)
    private String proid;//流程实例id

    @Column(length = 8)
    private String state;//状态

    @Column(updatable = false)
    private Date sttim = new Date();//开始时间

    private Date entim;//结束时间

}