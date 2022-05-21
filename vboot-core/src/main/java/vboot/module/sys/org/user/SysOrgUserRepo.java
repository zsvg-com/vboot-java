package vboot.module.sys.org.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SysOrgUserRepo extends JpaRepository<SysOrgUser,String> {

     SysOrgUser findByUsnam(String usnam);

     SysOrgUser findByMonum(String monum);

}

