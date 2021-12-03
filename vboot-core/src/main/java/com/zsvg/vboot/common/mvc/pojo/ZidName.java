package com.zsvg.vboot.common.mvc.pojo;


public class ZidName
{
    private String id;
    private String name;

    @Override
    public String toString() {
        return "ZidName{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public ZidName(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public ZidName()
    {
    }

    //get and set-------
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
