package vboot.module.sys.perm.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Yperm implements  Serializable
{
    private static final long serialVersionUID = 7712491509249951764L;

    private  int pos;
    private long code;
    private String id;
    private String tcode;

    //get and set------------
}
