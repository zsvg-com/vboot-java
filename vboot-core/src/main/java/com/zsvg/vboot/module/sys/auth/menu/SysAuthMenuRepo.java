package com.zsvg.vboot.module.sys.auth.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysAuthMenuRepo extends JpaRepository<SysAuthMenu, String> {

    List<SysAuthMenu> findByNameLikeOrderByOrnum(String name);

    List<SysAuthMenu> findByOrderByOrnum();

    List<SysAuthMenu> findByPid(String pid);

}

