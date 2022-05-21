package vboot.module.sys.job.log;

import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import lombok.extern.slf4j.Slf4j;
import vboot.common.mvc.api.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysJobLogService {

    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return ids.length;
    }

    @Transactional(readOnly = true)
    public SysJobLog findOne(String id) {
        return repo.findById(id).get();
    }

    @Autowired
    private SysJobLogRepo repo;


    @Autowired
    private JdbcDao jdbcDao;
}
