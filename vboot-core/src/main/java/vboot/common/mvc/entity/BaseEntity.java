package vboot.common.mvc.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {


    @Id
    @Column(length = 32)
    protected String id;

    @Column(length = 126)
    protected String name;//名称

}
