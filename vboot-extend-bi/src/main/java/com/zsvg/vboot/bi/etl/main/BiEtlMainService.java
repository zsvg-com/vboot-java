package com.zsvg.vboot.bi.etl.main;

import com.zsvg.vboot.common.mvc.service.BaseMainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

@Service
@Slf4j
public class BiEtlMainService extends BaseMainService<BiEtlMain> {

    @Value("${app.att.path}")
    private String ATT_PATH;

    public void runByNum(String kettleId,String num) throws Exception
    {
        BiEtlMain kettle=repo.findById(kettleId).get();
        try
        {
            KetUtil.runTran(ATT_PATH + "/" + kettle.getZpath(),null ,true);
        } catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            throw e;
        }
    }

    public long runById(String id, Map<String,String> map) throws Exception {
        BiEtlMain main=repo.findById(id).get();
       return KetUtil.runTran(ATT_PATH+"/"+main.getZpath(),map,false);
    }


    //bean注入------------------------------
    @Autowired
    private BiEtlMainRepo repo;

    @PostConstruct
    public void initDao() {
        super.setRepo(repo);
    }

}
