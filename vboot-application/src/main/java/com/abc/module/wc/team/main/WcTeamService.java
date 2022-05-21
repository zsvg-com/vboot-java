package com.abc.module.wc.team.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(rollbackFor = Exception.class)
public class WcTeamService {



    @Autowired
    private WcTeamRepo repo;


}
