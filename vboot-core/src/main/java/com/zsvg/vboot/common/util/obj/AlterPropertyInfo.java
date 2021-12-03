package com.zsvg.vboot.common.util.obj;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlterPropertyInfo implements Serializable {

    // 对应的属性名
    private String prnam;

    // 未修改之前的值
    private Object olval;

    // 修改后的值
    private Object neval;
}