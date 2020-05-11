package com.aubay.hackathon.repository;

import com.aubay.hackathon.model.table.ConsultantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends JpaRepository<ConsultantTable, Long> {

    List<ConsultantTable> findAllByOrderByViewsDesc();

    List<ConsultantTable> findAllByIsAuthorizedIsFalseOrderByLastViewAsc();

    List<ConsultantTable> findAllByIsAuthorizedIsFalseOrderByCreationDateAsc();
}
