package vboot.module.sys.todo.done;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class SysTodoDone {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String tid;//todoid

    @Column(length = 32)
    private String uid;//userid

    @Column(updatable = false)
    private Date entim = new Date();//完成时间
}
