package com.abc.module.te.prod.serie;

import vboot.common.mvc.service.BaseMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TeProdSerieService extends BaseMainService<TeProdSerie> {

    public List<TeProdSerie> findAllByCatid(String catid){
        return repo.findAllByCatid(catid);
    }

    //----------bean注入------------
    @Autowired
    private TeProdSerieRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
