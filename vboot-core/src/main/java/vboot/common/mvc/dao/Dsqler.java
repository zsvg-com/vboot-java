package vboot.common.mvc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dsqler extends BaseSqler{
    private String deleteClause = "";
    private String whereClause = "";
    private List<Object> whereParameters = new ArrayList<Object>();


    public Dsqler(String table)
    {
        this.deleteClause = "DELETE FROM " + table + " ";
    }


    public Dsqler addWhere(String condition, Object... params)
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
            throw new SQLException("为防止误删，delete 语句必须要有where条件");
        }
        return deleteClause  + whereClause;
    }

    public Object[] getParams()
    {
        return whereParameters.toArray();
    }


}
