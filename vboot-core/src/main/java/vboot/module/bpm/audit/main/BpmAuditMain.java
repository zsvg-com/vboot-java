package vboot.module.bpm.audit.main;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BpmAuditMain {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String facno;//当前节点ID:N1,N2

    @Column(length = 126)
    private String facna;//当前节点名称

    @Column(updatable = false)
    protected Date crtim = new Date();//开始时间

    @Column(length = 32)
    private String proid;//流程id

    @Column(length = 32)
    private String nodid;//节点id

    @Column(length = 32)
    private String tasid;//任务id

    @Column(length = 32)
    private String haman;//实处理人

//    @Column(length = 32)
//    private String auman;//授权处理人
//
//    @Column(length = 32)
//    private String exman;//应处理人

    @Column(length = 32)
    private String opkey;//操作的key：pass，refuse

    @Column(length = 64)
    private String opinf;//操作的名称: 通过，驳回，转办等

    @Column(length = 1000)
    private String opnot;//审核留言：具体写了什么审核内容


}