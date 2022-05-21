package vboot.module.sys.perm.menu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysPermMenuRepo extends JpaRepository<SysPermMenu, String> {

    List<SysPermMenu> findByNameLikeOrderByOrnum(String name);

    List<SysPermMenu> findByOrderByOrnum();

    List<SysPermMenu> findByPid(String pid);

}

