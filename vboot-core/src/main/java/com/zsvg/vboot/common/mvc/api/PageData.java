package com.zsvg.vboot.common.mvc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageData {

    private Integer total;

    private Object items;

}
