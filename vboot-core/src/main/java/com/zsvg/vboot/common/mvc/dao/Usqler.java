package com.zsvg.vboot.common.mvc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usqler extends BaseSqler
{
    private String updateClause = "";
    private String keyClause = "";
    private String whereClause = "";
    private List<Object> parameters = new ArrayList<Object>(); //参数列表
    private List<Object> whereParameters = new ArrayList<Object>();


    public Usqler(String table)
    {
        this.updateClause = "UPDATE " + table + " SET ";
    }

    public Usqler add(String key, Object value)
    {
        if ("true".equals(value))
        {
            keyClause += key + "=?,";
            parameters.add(true);
        } else if ("false".equals(value))
        {
            keyClause += key + "=?,";
            parameters.add(false);
        } else
        {
            keyClause += key + "=?,";
            parameters.add(value);
        }
        return this;
    }

    public Usqler addWhere(String condition, Object... params)
    {
        if (whereClause.length() == 0)
        {
            whereClause = " WHERE " + condition;
        } else
        {
            whereClause += " AND " + condition;
        }
        if (params != null && params.length > 0)
        {
            for (Object obj : params)
            {
                whereParameters.add(obj);
            }
        }
        return this;
    }

    public String getSql() throws SQLException {
        if("".equals(whereClause)){
            throw new SQLException("为防止误更新，update 语句必须要有where条件");
        }
        return updateClause + keyClause.substring(0, keyClause.length() - 1) + whereClause;
    }

    public Object[] getParams()
    {
        parameters.addAll(whereParameters);
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
