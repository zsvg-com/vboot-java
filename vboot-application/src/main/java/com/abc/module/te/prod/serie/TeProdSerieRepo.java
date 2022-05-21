package com.abc.module.te.prod.serie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeProdSerieRepo extends JpaRepository<TeProdSerie,String> {

     List<TeProdSerie> findAllByCatid(String catid);

}
