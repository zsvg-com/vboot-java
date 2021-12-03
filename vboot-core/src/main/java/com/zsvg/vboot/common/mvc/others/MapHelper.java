package com.zsvg.vboot.common.mvc.others;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapHelper {
    private Map<String,String> map;

    public MapHelper(){
        map = new LinkedHashMap<>();
    }

    public MapHelper(String field,String title){
        map = new LinkedHashMap<>();
        map.put("field", field);
        map.put("title", title);
    }

    public MapHelper put(String key,String value){
        map.put(key, value);
        return this;
    }

    public Map<String,String> get(){
        return map;
    }

    public MapHelper addTo(List list){
        list.add(map);
        return this;
    }

}
