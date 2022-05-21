package vboot.common.util.obj;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonDateValueProcessor implements JsonValueProcessor {
    @Override
    public Object processArrayValue(Object obj, JsonConfig jsonconfig) {
        return process(obj);
    }
    @Override
    public Object processObjectValue(String s, Object obj, JsonConfig jsonconfig) {
        return process(obj);
    }
    private Object process(Object obj) {
        if (obj == null) {// 如果时间为null，则返回空字串
            return "";
        }
        if (obj instanceof Date) {
            obj = new java.util.Date(((Date) obj).getTime());
        }
        if (obj instanceof java.util.Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINA);// 格式化时间为yyyy-MM-dd类型
            return sdf.format(obj);
        } else {
            return new Object();
        }
    }
}