package com.abc.module.te.prod.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Zscreen {

    private String id;

    private String name;

    private Integer ornum;

    List<Zparam> params = new ArrayList<>();

}
