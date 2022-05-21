package vboot.module.sys.org.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SysOrgGroupRepo extends JpaRepository<SysOrgGroup,String> {

    List<SysOrgGroup> findByNameLikeOrderByOrnum(String name);

    List<SysOrgGroup> findByOrderByOrnum();

}
