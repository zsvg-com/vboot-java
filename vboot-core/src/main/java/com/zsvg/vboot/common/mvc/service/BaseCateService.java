package com.zsvg.vboot.common.mvc.service;

import com.zsvg.vboot.common.mvc.api.RestResult;
import com.zsvg.vboot.common.mvc.dao.JdbcDao;
import com.zsvg.vboot.common.mvc.dao.Sqler;
import com.zsvg.vboot.common.mvc.pojo.ZidName;
import com.zsvg.vboot.common.mvc.pojo.Ztree;
import com.zsvg.vboot.common.mvc.entity.BaseCateEntity;
import com.zsvg.vboot.common.mvc.api.PageData;
import com.zsvg.vboot.common.mvc.pojo.Zinp;
import com.zsvg.vboot.common.util.lang.XstringUtil;
import com.zsvg.vboot.common.util.web.XuserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
public abstract class BaseCateService<T extends BaseCateEntity> {

    @Transactional(readOnly = true)
    public PageData findPageData(Sqler sqler) {
        sqler.selectCUinfo().addSelect("t.label");
        sqler.addOrder("t.ornum");
        return jdbcDao.findPageData(sqler);
    }

    @Transactional(readOnly = true)
    public List<ZidName> findIdNameRes(Sqler sqler) {
        return jdbcDao.findIdNameList(sqler);
    }



    //用户编辑时选择父类别，不能选择自己及孩子节点，否则会造成无线循环
    @Transactional(readOnly = true)
    public List<Zinp> inpChoose(String table, String id, String name){
        Sqler sqler = new Sqler(table);
        sqler.addSelect("pid");
        sqler.addWhere("t.avtag = 1");
        if (XstringUtil.isNotBlank(id)) {
            sqler.addWhere("t.tier not like ?", "%;" + id + ";%");
            sqler.addWhere("t.id <> ?", id);
        }
        if(XstringUtil.isNotBlank(name)){
            sqler.addLike("t.name", name);
        }
        sqler.addOrder("t.ornum");
        return jdbcDao.findInpList(sqler);
    }

    @Transactional(readOnly = true)
    public List<Ztree> tier2Choose(String table, String id, String name){
        Sqler sqler = new Sqler(table);
        sqler.addSelect("pid");
        sqler.addWhere("t.avtag = 1");
        if (XstringUtil.isNotBlank(id)) {
            sqler.addWhere("t.tier not like ?", "%;" + id + ";%");
            sqler.addWhere("t.id <> ?", id);
        }
        if(XstringUtil.isNotBlank(name)){
            sqler.addLike("t.name", name);
        }
        sqler.addOrder("t.ornum");
        return jdbcDao.findTreeList(sqler);
    }


    @Transactional(readOnly = true)
    public RestResult inpFind(String table){
        Sqler sqlHelper = new Sqler(table);
        sqlHelper.addSelect("pid");
        sqlHelper.addWhere("t.avtag = 1");
        sqlHelper.addOrder("t.ornum");
        return RestResult.ok(jdbcDao.findInpList(sqlHelper));
    }

    public RestResult inpDele(String table, String id){
        String sql = "select id from " + table + " where tier like ? order by tier";
        List<String> stringList = jdbcDao.findStringList(sql, "%;" + id + ";%");
        for (String sid : stringList) {
            repo.deleteById(sid);
        }
        repo.deleteById(id);
        return RestResult.ok();
    }

    public String insert(T cate) {
        if(cate.getId()==null||"".equals(cate.getId())){
            cate.setId(XstringUtil.getUUID());
        }
        if(cate.getCrman()==null){
            cate.setCrman(XuserUtil.getUser());
        }
        if (cate.getParent() != null) {
            T parent = repo.findById(cate.getParent().getId()).get();
            cate.setTier(parent.getTier()+parent.getId()+ ";");
        } else {
            cate.setTier("null;");
        }
        repo.save(cate);
        return cate.getId();
    }

    public String update(T cate){
        cate.setUptim(new Date());
        if(cate.getUpman()==null){
            cate.setUpman(XuserUtil.getUser());
        }
        repo.save(cate);
        return cate.getId();
    }


    public String update(T cate,String table){
        cate.setUptim(new Date());
        if(cate.getUpman()==null){
            cate.setUpman(XuserUtil.getUser());
        }
        String newPid = "";
        if(cate.getParent()!=null){
            newPid=cate.getParent().getId();
        }
        T oldCate=repo.findById(cate.getId()).get();
        String oldPid="";
        if(oldCate.getParent()!=null){
            oldPid  =oldCate.getParent().getId();
        }
        String oldTier=oldCate.getTier();
        if(oldPid.equals(newPid)){//如果父节点没有变，则只更新自己
//            System.out.println("父节点没有变");
            cate.setTier(oldTier);
            repo.save(cate);
        }else{//如果父节点变了，则另外需要更新自己孩子节点的tier层级
            String newTier = "null;";
            if(cate.getParent()!=null){
//                System.out.println("父节点变成其他点了");
                T newParentCate=repo.findById(cate.getParent().getId()).get();
                newTier =newParentCate.getTier()+newParentCate.getId()+";";
            }else{
//                System.out.println("父节点变成更节点了");
            }
            cate.setTier(newTier);
            repo.save(cate);
            //更新子节点
            String sql = "select id,tier as name from " + table + " where tier like ? and id<>?";
//            System.out.println(sql);
            List<ZidName> list = jdbcDao.findIdNameList(sql, oldTier+oldCate.getId()+";%",cate.getId());

            String updateSql = "update " + table + " set tier=? where id=?";
            List<Object[]> updateList = new ArrayList<Object[]>();
            for (ZidName ztwo : list) {
                Object[] arr = new Object[2];
                arr[0] = ztwo.getName().replace(oldTier,newTier);
                arr[1] = ztwo.getId();
                updateList.add(arr);
            }
            jdbcDao.batch(updateSql, updateList);
        }
        return cate.getId();

    }

    @Autowired
    protected JdbcDao jdbcDao;

    protected JpaRepository<T,String> repo;

    public void setRepo(JpaRepository<T,String> repo) {
        this.repo = repo;
    }

//    public T save(T t) {
//        return repo.save(t);
//    }
//
//    public T update(T t) {
//        return repo.saveAndFlush(t);
//    }
//
    @Transactional(readOnly = true)
    public T findOne(String id) {
        return repo.findById(id).get();
    }

    public int delete(String[] ids) {
        for (String id : ids) {
            repo.deleteById(id);
        }
        return ids.length;
    }
//
//    public void deleteOne(T t) {
//        repo.delete(t);
//    }
//
////    public boolean exists(String id) {
////        return baseDao.exists(id);
////    }
//
//    public long count() {
//        return baseDao.count();
//    }
//
//    public List<T> findAll() {
//        return baseDao.findAll();
//    }
//
//    public List<T> findAll(Sort sort) {
//        return baseDao.findAll(sort);
//    }
//
//    public List<T> findAll(Specification<T> specification) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public Page<T> findAll(Pageable pageable) {
//        return baseDao.findAll(pageable);
//    }
//
//    public Page<T> findAll(Specification<T> specification, Pageable pageable) {
//        // TODO Auto-generated method stub
//        return null;
//    }
}