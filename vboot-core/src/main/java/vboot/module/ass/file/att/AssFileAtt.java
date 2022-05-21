package vboot.module.ass.file.att;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Data
public class AssFileAtt {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String address;

    @Column(length = 128)
    private String pname;

    @Column(length = 128)
    private String sname;

    @Column(length = 16)
    private String zsize;

    @Transient
    private String zimg;

}
