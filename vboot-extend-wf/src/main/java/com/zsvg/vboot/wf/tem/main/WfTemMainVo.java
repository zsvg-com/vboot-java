package com.zsvg.vboot.wf.tem.main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class WfTemMainVo {
    private String id;

    private String name;

    @JsonIgnore
    private String pid;

    private List<WfTemMainVo> children;

    private String type;

}
