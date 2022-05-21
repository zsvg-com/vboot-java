package com.abc.module.te.prod.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Zmodel {

    private String id;

    private String name;

    private String catid;

    List<Zscreen> screens = new ArrayList<>();

}