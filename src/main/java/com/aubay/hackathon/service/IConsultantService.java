package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.filter.ConsultantFilter;
import com.aubay.hackathon.model.table.ConsultantTable;

import java.util.List;
import java.util.Optional;

public interface IConsultantService {

    ConsultantCore save(ConsultantCore consultantCore);

    Optional<ConsultantCore> getById(Long id);

    Optional<ConsultantCore> getByIdIncrementView(Long id);

    List<ConsultantCore> getFilteredConsultant(ConsultantFilter consultantFilter);

    List<ConsultantCore> findAllByOrderByViewsDesc();

    ConsultantCore findAllByAuthorizedIsFalseOrderByLastViewAsc();

    ConsultantCore findAllByAuthorizedIsFalseOrderByCreatedDateAsc();
}
