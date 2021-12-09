package com.zsvg.vboot.common.mvc.dao;


import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.config.security.pojo.Zuser;

import java.util.ArrayList;
import java.util.List;

public class Sqler
{
    private String fromClause = "";
    private String whereClause = "";
    private String orderClause = "";
    private String selectClause = "";
    private String groupClause = "";
    private Integer panum = 1;
    private Integer pasiz = 10;
    private int autoType=1;
    private List<Object> parameters = new ArrayList<Object>(); // 参数清单

    public Sqler()
    {

    }

    public Sqler(String table, Integer panum, Integer pasiz)
    {
        this.selectClause = "SELECT t.id,t.name";
        this.fromClause = " FROM " + table+" t";
        if (panum != null)
        {
            this.panum = panum;
        }
        if (pasiz != null)
        {
            this.pasiz = pasiz;
        }
    }

    public Sqler(String fields, String table, Integer panum, Integer pasiz)
    {
        this.selectClause = "SELECT " + fields;
        this.fromClause = " FROM " + table+" t";
        if (panum != null)
        {
            this.panum = panum;
        }
        if (pasiz != null)
        {
            this.pasiz = pasiz;
        }
    }


    public Sqler(String table)
    {
        this.selectClause = "SELECT t.id,t.name";
        this.fromClause = " FROM " + table+" t";
    }

    public Sqler(String fields, String table)
    {
        this.selectClause = "SELECT " + fields;
        this.fromClause = " FROM " + table+" t";
    }

    public void addSelect(String fields)
    {
        selectClause += "," + fields;
    }

    public Sqler addInnerJoin(String fields, String table, String condition)
    {
        if (!fields.equals(""))
        {
            selectClause += "," + fields;
        }
        fromClause += " INNER JOIN " + table + " ON " + condition;
        return this;
    }

    public Sqler addLeftJoin(String fields, String table, String condition)
    {
        if (!fields.equals(""))
        {
            selectClause += "," + fields;
        }
        fromClause += " Left JOIN " + table + " ON " + condition;
        return this;
    }

    public Sqler addWhere(String condition, Object... params)
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
                parameters.add(obj);
            }
        }
        return this;
    }

    public Sqler addWhere(boolean append, String condition, Object... params)
    {
        if (append)
        {
            addWhere(condition, params);
        }
        return this;
    }

    public Sqler addEqual(String name, Object value)
    {
        if (XstringUtil.isNotBlank(value + ""))
        {
            if (whereClause.length() == 0)
            {
                whereClause = " WHERE " + name + " = ?";
            } else
            {
                whereClause += " AND " + name + " = ?";
            }
            parameters.add(value);
        }
        return this;
    }

    public Sqler addLike(String name, Object value)
    {
        if (XstringUtil.isNotBlank(value + ""))
        {
            if (whereClause.length() == 0)
            {
                whereClause = " WHERE " + name + " like ?";
            } else
            {
                whereClause += " AND " + name + " like ?";
            }
            parameters.add("%"+(""+value).trim()+"%");
        }
        return this;
    }

    public Sqler addGtStringDate(String name, Object value)
    {
        if (XstringUtil.isNotBlank(value + ""))
        {
            if (whereClause.length() == 0)
            {
                whereClause = " WHERE " + name + ">=?";
            } else
            {
                whereClause += " AND " + name + ">=?";
            }
            parameters.add(value);
        }
        return this;
    }

    public Sqler addGtDate(String name, Object value, String dbType)
    {
        if("ORACLE".equals(dbType)){
            if (XstringUtil.isNotBlank(value + ""))
            {
                if (whereClause.length() == 0)
                {
                    whereClause = " WHERE " + name + ">=to_date(?,'yyyy-MM-dd')";
                } else
                {
                    whereClause += " AND " + name + ">=to_date(?,'yyyy-MM-dd')";
                }
                parameters.add(value);
            }
        }else if("MYSQL".equals(dbType)){
            if (XstringUtil.isNotBlank(value + ""))
            {
                if (whereClause.length() == 0)
                {
                    whereClause = " WHERE unix_timestamp(" + name + ") >=unix_timestamp(?)";
                } else
                {
                    whereClause += " AND unix_timestamp(" + name + ")  >=unix_timestamp(?)";
                }
                parameters.add(value);
            }
        }

        return this;
    }

    public Sqler addLtDate(String name, Object value, String dbType)
    {
        if("ORACLE".equals(dbType)) {
            if (XstringUtil.isNotBlank(value + "")) {
                if (whereClause.length() == 0) {
                    whereClause = " WHERE " + name + "<to_date(?,'yyyy-MM-dd')+1";
                } else {
                    whereClause += " AND " + name + "<to_date(?,'yyyy-MM-dd')+1";
                }
                parameters.add(value);
            }
        }else{
            if (XstringUtil.isNotBlank(value + "")) {
                if (whereClause.length() == 0) {
                    whereClause = " WHERE unix_timestamp(" + name + ") <=unix_timestamp(?)";
                } else {
                    whereClause += " AND unix_timestamp(" + name + ") <=unix_timestamp(?)";
                }
                parameters.add(value+" 23:59:59");
            }
        }
        return this;
    }

    public Sqler addLtStringDate(String name, Object value)
    {
        if (XstringUtil.isNotBlank(value + ""))
        {
            if (whereClause.length() == 0)
            {
                whereClause = " WHERE " + name + "<=?";
            } else
            {
                whereClause += " AND " + name + "<=?";
            }
            parameters.add(value);
        }
        return this;
    }

    public Sqler addOrder(String propertyName)
    {
        if (orderClause.length() == 0)
        {
            orderClause = " ORDER BY " + propertyName;
        } else
        {
            orderClause += ", " + propertyName;
        }
        return this;
    }

    public Sqler addDescOrder(String propertyName)
    {
        if (orderClause.length() == 0)
        {
            orderClause = " ORDER BY " + propertyName + " DESC";
        } else
        {
            orderClause += ", " + propertyName + " DESC";
        }
        return this;
    }

    public Sqler addGroup(String gclause)
    {
        groupClause=" GROUP BY "+gclause;
        return this;
    }

    public Sqler addJoin(String joinClause)
    {
        this.fromClause += " " + joinClause;
        return this;
    }

    public Sqler addAuth(Zuser zuser, String table)
    {
        addJoin(" left join "+table+"_viewer vvv on vvv.id=t.id");
        addJoin(" left join "+table+"_editor eee on eee.id=t.id");
        if (!zuser.isAdmin())
        {
            addWhere("(t.crman=? or vvv.org in (" + zuser.getConds() + ") or eee.org in (" + zuser.getConds() + "))", zuser.getId());
        }
        return this;
    }

    public Sqler selectCinfo(){
        addLeftJoin("oo1.name as crman,t.crtim", "sys_org oo1", "oo1.id=t.crman");
        return this;
    }


    public Sqler selectCUinfo(){
        addLeftJoin("oo1.name as crman,t.crtim", "sys_org oo1", "oo1.id=t.crman");
        addLeftJoin("oo2.name as upman,t.uptim", "sys_org oo2", "oo2.id=t.upman");
        return this;
    }



    //get and set-------------------------------------------

    public String getSql()
    {
        return selectClause + fromClause + whereClause +groupClause+ orderClause;
    }

    public String getLowerCaseSql()
    {
        //字段转成小写
        StringBuilder changeSelectClause= new StringBuilder();
        String[] strArr= selectClause.split(",");
        lowerCaseSelect(changeSelectClause, strArr);
        changeSelectClause.deleteCharAt(changeSelectClause.length()-1);
        return changeSelectClause.toString()  + fromClause + whereClause+groupClause + orderClause;
    }

    public Sqler getNewGroupSqler(String groupClause){
        Sqler sqler = new Sqler("");
        sqler.fromClause=this.fromClause;
        sqler.whereClause=this.whereClause;
        sqler.groupClause=" group by "+groupClause;
        return sqler;
    }


    public String getSizeSql()
    {
        if("".equals(groupClause)){
            return "SELECT count(1)" + fromClause + whereClause;
        }
        else {
            return  "SELECT count(1) FROM (SELECT 1 " + fromClause + whereClause+groupClause+") SSZZ";
        }
    }

    public String getMysqlPagingSql()
    {
        int fromIndex = pasiz * (panum - 1);
        return selectClause + fromClause + whereClause+groupClause + orderClause + " limit " + fromIndex + "," + pasiz;
    }


    public String getOraclePagingSql()
    {
        int rownum = panum * pasiz;
        int rn = (panum - 1) * pasiz;
        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + selectClause + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
//        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + changeSelectClause.toString() + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
    }

    public String getOraclePagingLowerCaseSql()
    {
        //字段转成小写
        StringBuilder changeSelectClause= new StringBuilder();
        String[] strArr= selectClause.split(",");
        lowerCaseSelect(changeSelectClause, strArr);
        changeSelectClause.deleteCharAt(changeSelectClause.length()-1);
        //分页
        int rownum = panum * pasiz;
        int rn = (panum - 1) * pasiz;
        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + changeSelectClause.toString() + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
    }

    private void lowerCaseSelect(StringBuilder changeSelectClause, String[] strArr) {
        for (String aStrArr : strArr) {
            if(aStrArr.contains("(")&&!aStrArr.contains(")")){
                changeSelectClause.append(" ").append(aStrArr);
            }else if(aStrArr.contains("SELECT")){
                String[] firstArr = aStrArr.split(" ");
                if(firstArr.length>=3){
                    firstArr[firstArr.length - 1] = "\"" + firstArr[firstArr.length - 1] + "\"";
                }else{
                    firstArr[1]=firstArr[1]+" \"" + firstArr[1].substring(firstArr[1].lastIndexOf(".")+1) + "\"";
                }
                for (String afirstArr : firstArr) {
                    changeSelectClause.append(" ").append(afirstArr);
                }
            }else{

                String[] strArr2 = aStrArr.split(" ");
                if(strArr2.length>=2){
                    strArr2[strArr2.length - 1] = "\"" + strArr2[strArr2.length - 1] + "\"";
                }else{
                    strArr2[0]=strArr2[0]+" \"" + strArr2[0].substring(strArr2[0].lastIndexOf(".")+1) + "\"";
                }
                for (String aStrArr2 : strArr2) {
                    changeSelectClause.append(" ").append(aStrArr2);
                }
            }
            changeSelectClause.append(",");
        }
    }


//    public String getOraclePagingSql()
//    {
//        int rownum = panum * pasiz;
//        int rn = (panum - 1) * pasiz;
//        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + selectClause + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
////        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + changeSelectClause.toString() + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
//    }
//
//    public String getOraclePagingLowerCaseSql()
//    {
//        //字段转成小写
//        StringBuilder changeSelectClause= new StringBuilder();
//        String[] strArr= selectClause.split(",");
//        lowerCaseSelect(changeSelectClause, strArr);
//        changeSelectClause.deleteCharAt(changeSelectClause.length()-1);
//        //分页
//        int rownum = panum * pasiz;
//        int rn = (panum - 1) * pasiz;
//        return " SELECT * FROM (SELECT PPGG.*, ROWNUM RN FROM (" + changeSelectClause.toString() + fromClause + whereClause+groupClause + orderClause + ") PPGG  WHERE ROWNUM <= " + rownum + ")  WHERE RN > " + rn;
//    }
//
//    private void lowerCaseSelect(StringBuilder changeSelectClause, String[] strArr) {
//        for (String aStrArr : strArr) {
//            if(aStrArr.contains("(")&&!aStrArr.contains(")")){
//                changeSelectClause.append(" ").append(aStrArr);
//            }else{
//                String[] strArr2 = aStrArr.split(" ");
//                strArr2[strArr2.length - 1] = "\"" + strArr2[strArr2.length - 1] + "\"";
//                for (String aStrArr2 : strArr2) {
//                    changeSelectClause.append(" ").append(aStrArr2);
//                }
//            }
//            changeSelectClause.append(",");
//        }
//    }

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
    //get and set-----------------------------------------------


    public Integer getPanum() {
        return panum;
    }

    public Integer getPasiz() {
        return pasiz;
    }

    public int getAutoType() {
        return autoType;
    }

    public void setAutoType(int autoType) {
        this.autoType = autoType;
    }

    public void setPanum(Integer panum) {
        this.panum = panum;
    }

    public void setPasiz(Integer pasiz) {
        this.pasiz = pasiz;
    }
}
