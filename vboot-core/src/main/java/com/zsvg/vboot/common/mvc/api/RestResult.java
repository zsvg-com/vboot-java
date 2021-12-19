package com.zsvg.vboot.common.mvc.api;


import java.util.ArrayList;
import java.util.HashMap;

public class RestResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";

    public static final String MSG_TAG = "message";

    public static final String DATA_TAG = "result";

    public RestResult() {

    }

    public RestResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public RestResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static RestResult ok() {
        return RestResult.ok("操作成功");
    }

    public static RestResult ok(Object data) {
        return RestResult.ok("操作成功", data);
    }

    public static RestResult empty() {
        return RestResult.ok("操作成功", new ArrayList<>());
    }

    public static RestResult ok(String msg, Object data) {
        return new RestResult(0, msg, data);
    }

    public static RestResult error(Object data) {
        return RestResult.error("操作失败", data);
    }

    public static RestResult error(String msg, Object data) {
        return new RestResult(500, msg, data);
    }

    public static RestResult build(int code, String msg) {
        return new RestResult(code, msg, null);
    }

    public static RestResult build(int code, String msg, Object data) {
        return new RestResult(code, msg, data);
    }
}
