package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.filter.ConsultantFilter;

import java.util.List;
import java.util.Optional;

public interface IConsultantService {

    ConsultantCore save(ConsultantCore consultantCore);

    Optional<ConsultantCore> getById(Long id);

    List<ConsultantCore> getFilteredConsultant(ConsultantFilter consultantFilter);
}
