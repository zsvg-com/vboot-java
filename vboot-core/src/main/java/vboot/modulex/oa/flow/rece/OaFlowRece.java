package vboot.modulex.oa.flow.rece;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(indexes = {@Index(columnList = "userid"),@Index(columnList = "flowid")})
public class OaFlowRece {

    @Id
    @Column(length = 32)
    private String id;//ID

    @Column(length = 32)
    private String userid;//用户ID

    @Column(length = 32)
    private String flowid;//最近使用的流程ID

    @Column
    private Date uptim = new Date();//最近使用时间
}
