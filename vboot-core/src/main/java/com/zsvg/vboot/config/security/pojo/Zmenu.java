package com.zsvg.vboot.config.security.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Zmenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @JSONField(serialize = false)
    private String id;

    @JsonIgnore
    @JSONField(serialize = false)
    private String pid;

    @JsonIgnore
    @JSONField(serialize = false)
    private String perm;

    private String path;

    private String name;

    private String component;

    private Zmeta meta;

    private String redirect;

    private List<Zmenu> children;

}
