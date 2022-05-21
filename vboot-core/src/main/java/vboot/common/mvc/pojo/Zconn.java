package vboot.common.mvc.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zconn {
    private String type;

    private String url;

    private String username;

    private String passcode;



    public String getSid()
    {
        if(isMysql())
        {
            return url.substring(url.lastIndexOf("/")+1).replaceAll("/","");
        }
        else if (isOracle())
        {
            return url.substring(url.lastIndexOf(":")+1);
        }
        else if (isDB2())
        {
            return url.substring(url.lastIndexOf("/")+1);
        }
        else{
            return null;
        }
    }

    public String getDriver(){
        if(isOracle()){
            return "oracle.jdbc.driver.OracleDriver";
        }else if(isMysql()){
            return "com.mysql.jdbc.Driver";
        }else if(isMysql8()){
            return "com.mysql.cj.jdbc.Driver";
        }else if(isDB2()){
            return "com.ibm.db2.jcc.DB2Driver";
        }else{
            return "暂不支持";
        }
    }

    public boolean isOracle()
    {
        return "oracle".equals(type);
    }
    public boolean isMysql()
    {
        return "mysql".equals(type);
    }
    public boolean isMysql8()
    {
        return "mysql8".equals(type);
    }
    public boolean isDB2()
    {
        return "DB2".equals(type);
    }
}
