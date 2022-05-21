package vboot.common.util.obj;

import lombok.Data;

@Data
public class PropertyModelInfo {
    //属性名
    private String prnam;
    // 属性值
    private Object value;
    // 返回值类型
    private Class<?> retyp;
}