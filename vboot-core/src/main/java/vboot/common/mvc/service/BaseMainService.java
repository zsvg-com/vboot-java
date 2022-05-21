package vboot.common.mvc.service;

import vboot.common.mvc.api.PageData;
import vboot.common.mvc.dao.JdbcDao;
import vboot.common.mvc.dao.Sqler;
import vboot.common.mvc.entity.BaseMainEntity;
import vboot.common.util.lang.XstringUtil;
import vboot.common.util.web.XuserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
public abstract class BaseMainService<T extends BaseMainEntity> {

    //---------------------------------------查询-------------------------------------
    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        if (sqler.getAutoType() == 1) {
            sqler.selectCUinfo();
            sqler.addOrder("t.crtim desc");
        }
        return jdbcDao.findPageData(sqler);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findMapList(Sqler sqler) {
        return jdbcDao.findMapList(sqler);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findMapList(String sql, Object... args) {
        return jdbcDao.findMapList(sql, args);
    }

    @Transactional(readOnly = true)
    public boolean existsById(String id) {
        return repo.existsById(id);
    }

    @Transactional(readOnly = true)
    public T findOne(String id) {
        return repo.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repo.findAll();
    }

    //---------------------------------------增删改-------------------------------------
    public T insert(T main)  {
        if (main.getId() == null || "".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
        }
        main.setCrtim(new Date());
        if (main.getCrman() == null) {
            main.setCrman(XuserUtil.getUser());
        }
        return repo.save(main);
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return ids.length;
    }

    public T update(T main) {
        main.setUptim(new Date());
        if (main.getUpman() == null) {
            main.setUpman(XuserUtil.getUser());
        }
        return repo.save(main);
    }

    public T save(T t) {
        return repo.save(t);
    }


    //---------------------------------------bean注入-------------------------------------
    @Autowired
    protected JdbcDao jdbcDao;

    protected JpaRepository<T, String> repo;

    public void setRepo(JpaRepository<T, String> repo) {
        this.repo = repo;
    }


}
