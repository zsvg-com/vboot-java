package com.abc.module.te.feat.comp;

import com.abc.module.te.allot.param.TeAllotParamRepo;
import vboot.common.mvc.service.BaseMainService;
import vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TeFeatCompService extends BaseMainService<TeFeatComp> {

    public TeFeatComp insert(TeFeatComp main){
        if(main.getPatem()!=null){
            String puuid = XstringUtil.getUUID();
            main.getPatem().setId(puuid);
            main.getPatem().setName(main.getName()+"专用");
            main.getPatem().setSptag(true);
            apRepo.save(main.getPatem());
            main.setPatid(puuid);
        }
        return super.insert(main);
    }

    public TeFeatComp update(TeFeatComp main){
        if(main.getPatem()!=null){
            apRepo.save(main.getPatem());
        }
        return super.update(main);
    }

    //----------bean注入------------
    @Autowired
    private TeAllotParamRepo apRepo;

    @Autowired
    private TeFeatCompRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
