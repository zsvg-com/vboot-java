package com.zsvg.vboot.common.mvc.dao;

import java.util.ArrayList;
import java.util.List;

public class Isqler extends BaseSqler
{
    private String insertClause = "";
    private String keyClause = "";
    private String valueClause = "";
    private List<Object> parameters = new ArrayList<Object>();

    public Isqler(String table)
    {
        this.insertClause = "INSERT INTO " + table;
    }

    public Isqler add(String key, Object value)
    {
        if ("true".equals(value))
        {
            keyClause += key + ",";
            valueClause += "?,";
            parameters.add(true);
        } else if ("false".equals(value))
        {
            keyClause += key + ",";
            valueClause += "?,";
            parameters.add(false);
        } else
        {
            keyClause += key + ",";
            valueClause += "?,";
            parameters.add(value);
        }
        return this;
    }

    public String getSql()
    {
        return insertClause + "(" + keyClause.substring(0, keyClause.length() - 1) + ")" + " VALUES(" + valueClause.substring(0, valueClause.length() - 1) + ")";
    }

    public Object[] getParams()
    {
        return parameters.toArray();
    }

    public void testParams()
    {
        for (int i = 0; i < parameters.size(); i++)
        {
            if (i == parameters.size() - 1)
            {
                System.out.println(parameters.get(i) + ";");
            } else
            {
                System.out.print(parameters.get(i) + ",");
            }
        }
    }
}
