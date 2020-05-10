package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.ConsultantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultantRepository extends JpaRepository<ConsultantTable, Long> {

}
