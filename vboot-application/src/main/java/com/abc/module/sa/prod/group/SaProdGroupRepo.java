package com.abc.module.sa.prod.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaProdGroupRepo extends JpaRepository<SaProdGroup,String> {

    List<SaProdGroup> findAllByProjid(String projid);
}
