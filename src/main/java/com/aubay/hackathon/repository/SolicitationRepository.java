package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.SolicitationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<SolicitationTable, Long> {

    
}
