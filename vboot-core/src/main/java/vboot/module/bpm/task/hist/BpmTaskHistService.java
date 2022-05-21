package vboot.module.bpm.task.hist;


import vboot.common.mvc.dao.JdbcDao;
import vboot.module.bpm.task.main.BpmTaskMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class BpmTaskHistService {

    public BpmTaskHist createTask(BpmTaskMain mainTask) {
        BpmTaskHist histTask = new BpmTaskHist();
        histTask.setId(mainTask.getId());
        histTask.setProid(mainTask.getProid());
        histTask.setState("20");
        histTask.setExman(mainTask.getExman());
        histTask.setNodid(mainTask.getNodid());
        histTask.setType("1");
        return repo.save(histTask);
    }

    @Transactional(readOnly = true)
    public BpmTaskHist findOne(String id) {
        return repo.findById(id).get();
    }

    public void delete(String id) {
         repo.deleteById(id);
    }


    @Autowired
    protected JdbcDao jdbcDao;


    @Autowired
    private BpmTaskHistRepo repo;
}
