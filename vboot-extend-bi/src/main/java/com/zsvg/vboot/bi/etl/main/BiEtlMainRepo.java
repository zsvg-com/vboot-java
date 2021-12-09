package com.zsvg.vboot.bi.etl.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BiEtlMainRepo extends JpaRepository<BiEtlMain,String> {

      BiEtlMain findByCode(String code);

}
