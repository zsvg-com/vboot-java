package vboot.module.sys.todo.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class SysTodoUser {

    @Id
    @Column(length = 32)
    private String id;//主键

    @Column(length = 32)
    private String tid;//todoid

    @Column(length = 32)
    private String uid;//userid
}
