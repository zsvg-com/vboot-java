package com.zsvg.vboot.common.mvc.service;

import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.api.PageData;
import com.zsvg.vboot.common.mvc.entity.BaseEntity;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
public abstract class BaseService<T extends BaseEntity> {

    //---------------------------------------查询-------------------------------------
    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        return jdbcDao.findPageData(sqler);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findMapList(Sqler sqler) {
        return jdbcDao.findMapList(sqler);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findMapList(String sql,Object... args) {
        return jdbcDao.findMapList(sql,args);
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
    public T insert(T main) {
        if (main.getId() == null||"".equals(main.getId())) {
            main.setId(XstringUtil.getUUID());
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
