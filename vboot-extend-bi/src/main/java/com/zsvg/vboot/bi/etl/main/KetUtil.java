package com.zsvg.vboot.bi.etl.main;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.TransLogTable;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.util.HashMap;
import java.util.Map;

public class KetUtil
{
    public static void runJob(String jobPath)
    {
        try
        {
            JobMeta jobMeta = new JobMeta(jobPath, null);
            Job job = new Job(null, jobMeta);
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0)
            {
                throw new Exception(
                        "There are errors during job exception!(执行job发生异常)");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static long runTran(String ktrPath,Map<String,String> map,Boolean waitFlag) throws Exception
    {
        //如何保存到mysql8里
        TransMeta transMeta = new TransMeta(ktrPath);

        DatabaseMeta databaseMeta = new DatabaseMeta();

//        databaseMeta.setName("ket");
//        databaseMeta.setDatabaseType("MYSQL");
//        databaseMeta.setAccessType(DatabaseMeta.TYPE_ACCESS_NATIVE);
//        databaseMeta.setHostname("localhost");
//        databaseMeta.setDBName("vboot-java");
//        databaseMeta.setDBPort("3306");
//        databaseMeta.setUsername("root");
//        databaseMeta.setPassword("123456");
//        transMeta.addDatabase(databaseMeta);

        VariableSpace space = new Variables();
        TransLogTable transLogTable = TransLogTable.getDefault(space,transMeta,transMeta.getSteps());
        transLogTable.setConnectionName("VBOOT");
//        transLogTable.setSchemaName("数据库的SCHEMA");
        transLogTable.setTableName("bi_etl_log");
        transMeta.setTransLogTable(transLogTable);
        Trans trans = new Trans(transMeta);
//        trans.setLogLevel(LogLevel.ERROR);
        if(map!=null){
            for (String s : map.keySet())
            {
                trans.setVariable(s,map.get(s));
            }
        }
        trans.execute(null);
        if(waitFlag){
            trans.waitUntilFinished();
        }
        return trans.getBatchId();
    }


    public static void main(String[] args) throws Exception
    {
        KettleEnvironment.init();
//        KetUtil.runTran("C:/xx.ktr",null ,true);
//        Map<String, String> map = new HashMap<>();
//        map.put("i_vbelh","SA00132095");
        KetUtil.runTran("C:/vboot/att/2021/12/08/2/f3b2003c982e402ca4fda213a60e73f7.ktr",null ,false);
//        KetUtil.runTran(null, "C:/xx.ktr");
//        runJob("C:\\bb.kjb");
    }
}
