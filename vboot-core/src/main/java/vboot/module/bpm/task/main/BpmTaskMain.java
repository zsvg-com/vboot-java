package vboot.module.bpm.task.main;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class BpmTaskMain {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String type;//任务类型

    @Column(length = 32)
    private String proid;//流程实例id

    private String nodid;//节点id

    @Column(updatable = false)
    private Date sttim = new Date();//开始时间

    @Column(length = 8)
    private String notty;//消息类型

    @Column(length = 8)
    private String state;//状态

    private Integer ornum;//排序

    private Boolean actag;//是否激活

    @Column(length = 32)
    private String haman;//实处理人

    @Column(length = 32)
    private String auman;//授权处理人

    @Column(length = 32)
    private String exman;//应处理人


}
