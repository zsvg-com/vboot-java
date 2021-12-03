package com.zsvg.vboot.common.mvc.pojo;

public class Zinp {
    private String id;
    private String name;
    private String pid;

    public Zinp() {
    }

    public Zinp(String id) {
        this.id = id;
    }

    public Zinp(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Zinp(String id, String name, String pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
