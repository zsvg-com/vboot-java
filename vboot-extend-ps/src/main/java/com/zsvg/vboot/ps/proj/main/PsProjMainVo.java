package com.zsvg.vboot.ps.proj.main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class PsProjMainVo {
    private String id;

    private String name;

    @JsonIgnore
    private String pid;

    private List<PsProjMainVo> children;

    private String type;

}