package vboot.config.security.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Zmeta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private String affix;

    private String icon;

    private Integer orderNo;

    private Boolean ignoreKeepAlive;

}
