package com.zsvg.vboot.module.sys.org.dept;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysOrgDeptRepo extends JpaRepository<SysOrgDept,String> {

    List<SysOrgDept> findByNameLikeOrderByOrnum(String name);

    List<SysOrgDept> findByOrderByOrnum();

    List<SysOrgDept> findByParent(String pid);

}
