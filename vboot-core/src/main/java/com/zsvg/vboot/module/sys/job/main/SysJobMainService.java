package com.zsvg.vboot.module.sys.job.main;

import com.zsvg.vboot.common.util.lang.XscanUtil;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.module.sys.job.root.IJobGroup;
import lombok.extern.slf4j.Slf4j;
import com.zsvg.vboot.module.sys.job.root.IJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysJobMainService {

    public List<SysJobMain> getJobList(){
        List<SysJobMain> scanJobList = getScanJobList();
        List<SysJobMain> dbJobList = repo.findAll();
        for (int j = 0; j < dbJobList.size(); j++) {
            boolean flag = false;
            for (int i = 0; i < scanJobList.size(); i++) {
                if(dbJobList.get(j).getId().equals(scanJobList.get(i).getId())){
                    flag=true;
                }
            }
            if(!flag) {
                repo.deleteById(dbJobList.get(j).getId());//删除已移除的JOB
            }
        }

        for (int i = 0; i < scanJobList.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < dbJobList.size(); j++) {
                if(dbJobList.get(j).getId().equals(scanJobList.get(i).getId())){
                    scanJobList.get(i).setCron(dbJobList.get(j).getCron());
                    scanJobList.get(i).setState(dbJobList.get(j).getState());
                    flag=true;
                }
            }
            if(!flag){
                repo.save(scanJobList.get(i));//增加新配置的JOB
            }
        }
        return scanJobList;
    }

    private List<SysJobMain> getScanJobList()
    {
        List<SysJobMain> list = new ArrayList<SysJobMain>();
        Set<Class<?>> classes = XscanUtil.getClasses("com.zsvg");
        for (Class<?> clazz : classes)
        {
            IJobGroup group = clazz.getAnnotation(IJobGroup.class);
            if (group != null)
            {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods)
                {
                    IJob xjob = method.getAnnotation(IJob.class);
                    if (xjob != null)
                    {
                        SysJobMain job = new SysJobMain();
                        job.setCron(xjob.cron());
                        job.setJgroup(XstringUtil.toLowerfirst(clazz.getSimpleName()));
                        job.setName(xjob.name());
                        job.setJid(method.getName());
                        job.setState("是");
                        if(XstringUtil.isBlank(xjob.key())){
                            job.setId(job.getJgroup()+":"+method.getName());
                        }else{
                            job.setId(xjob.key());
                        }
                        boolean flag=false;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).getId().equals(job.getId())){
                                flag=true;
                                log.warn("存在相同key的job，已屏蔽");
                                break;
                            }
                        }
                        if(!flag){
                            list.add(job);
                        }

                    }
                }
            }
        }
        return list;
    }

    @Autowired
    private SysJobMainRepo repo;
}
