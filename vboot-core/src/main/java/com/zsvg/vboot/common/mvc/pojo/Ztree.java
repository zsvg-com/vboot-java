package com.zsvg.vboot.common.mvc.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Ztree {
    private String id;

    private String name;

    @JsonIgnore
    private String pid;

    private List<Ztree> children;

    private Map<String, Object> data;

}