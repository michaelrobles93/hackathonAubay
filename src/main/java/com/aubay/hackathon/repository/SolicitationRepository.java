package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.SolicitationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<SolicitationTable, Long> {

    @Query("SELECT COUNT(requirement.id) FROM REQUIREMENT requirement")
    Long getAllSolicitation();

    @Query("SELECT COUNT(requirement.id) FROM REQUIREMENT requirement WHERE requirement.isAuthorized = true")
    Long getAllSolicitationAuthorized();
}
