package com.zsvg.vboot.bi.etl.main;

import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.TransLogTable;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.util.Map;

public class KetUtil {
    public static void runJob(String jobPath) {
        try {
            JobMeta jobMeta = new JobMeta(jobPath, null);
            Job job = new Job(null, jobMeta);
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                throw new Exception(
                        "There are errors during job exception!(执行job发生异常)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long runTran(String ktrPath, Map<String, String> map, Boolean waitFlag) throws Exception {
        TransMeta transMeta = new TransMeta(ktrPath);

        DatabaseMeta databaseMeta = new DatabaseMeta();
        databaseMeta.setName("ket");
        databaseMeta.setDatabaseType("MYSQL");
        databaseMeta.setAccessType(DatabaseMeta.TYPE_ACCESS_NATIVE);


        databaseMeta.setDBPort("3306");
        databaseMeta.setUsername("root");
        databaseMeta.setHostname("localhost");
        databaseMeta.setDBName("vboot-java");
        databaseMeta.setPassword("123456");

        databaseMeta.getAttributes().setProperty("EXTRA_OPTION_MYSQL.useSSL", "false");
        transMeta.addDatabase(databaseMeta);
        VariableSpace space = new Variables();
        TransLogTable transLogTable = TransLogTable.getDefault(space, transMeta, transMeta.getSteps());
        transLogTable.setConnectionName("ket");
        transLogTable.setTableName("bi_etl_log");
        transMeta.setTransLogTable(transLogTable);

        transMeta.setCapturingStepPerformanceSnapShots(true);
        Trans trans = new Trans(transMeta);

//        trans.setLogLevel(LogLevel.DEBUG);
        trans.setMonitored(true);
        trans.setInitializing(true);
        trans.setPreparing(true);
        trans.setRunning(true);
        trans.setSafeModeEnabled(true);
        if (map != null) {
            for (String s : map.keySet()) {
                trans.setVariable(s, map.get(s));
            }
        }
        trans.execute(null);
        if (waitFlag) {
            trans.waitUntilFinished();
        }

//        if (trans.isFinished()) {
//            Map<String, List<StepPerformanceSnapShot>> stepPerformanceSnapShots = trans.getStepPerformanceSnapShots();
//            stepPerformanceSnapShots.forEach((str, StepPerformanceSnapShotList) -> {
//                for (StepPerformanceSnapShot stepPerformanceSnapShot : StepPerformanceSnapShotList) {
//                    System.out.println(JSONObject.fromObject(stepPerformanceSnapShot).toString());
//                }
//            });
//            System.out.println("执行成功");
//        } else {
//            Map<String, List<StepPerformanceSnapShot>> stepPerformanceSnapShots = trans.getStepPerformanceSnapShots();
//            stepPerformanceSnapShots.forEach((str, StepPerformanceSnapShotList) -> {
//                for (StepPerformanceSnapShot stepPerformanceSnapShot : StepPerformanceSnapShotList) {
//                    System.err.println(JSONObject.fromObject(stepPerformanceSnapShot).toString());
//                }
//            });
//            System.out.println("执行失败");
//        }
        KettleLogStore.discardLines(trans.getLogChannelId(), true);
        return trans.getBatchId();
    }


}
