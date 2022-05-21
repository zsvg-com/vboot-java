package vboot.module.bpm.node.main;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BpmNodeMain {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String facno;//当前节点编号:N1,N2

    @Column(length = 126)
    private String facna;//当前节点名称

    @Column(length = 32)
    private String facty;//当前节点类型

    @Column(length = 32)
    private String proid;//流程实例id

    @Column(length = 8)
    private String state;//状态

    @Column(updatable = false)
    protected Date sttim = new Date();//开始时间

}