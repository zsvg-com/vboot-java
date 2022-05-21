package vboot.module.bpm.proc.param;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmProcParamService {

    public void save(BpmProcParam param){
        repo.save(param);
    }

    public void delete(String id){
        repo.deleteById(id);
    }



    @Autowired
    private BpmProcParamRepo repo;
}
