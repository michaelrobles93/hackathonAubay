package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.CidadeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeTable, Long> {


}
