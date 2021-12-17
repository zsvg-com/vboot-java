package com.zsvg.vboot.ps.task.list;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PsTaskListService extends BaseMainService<PsTaskList> {


    //----------bean注入------------
    @Autowired
    private PsTaskListRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }
}