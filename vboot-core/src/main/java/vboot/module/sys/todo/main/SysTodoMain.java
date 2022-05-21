package vboot.module.sys.todo.main;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class SysTodoMain {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 256)
    private String name;//主题

    @Column(length = 8)
    private String type;//类型

    @Column(length = 8)
    private String level;//紧急度

    @Column(length = 128)
    private String modca;//模型分类

    @Column(length = 32)
    private String modid;//模型ID

    @Column(length = 512)
    private String link;//链接

    private String notes;//备注

    @Column(updatable = false)
    private Date crtim = new Date();//创建时间

    @Column(updatable = false)
    private String crman;//创建人

//    @ManyToMany
//    @JoinTable(name = "sys_todo_user", joinColumns = {@JoinColumn(name = "tid")},
//            inverseJoinColumns = {@JoinColumn(name = "uid")})
//    private List<SysOrg> tamen;

}
