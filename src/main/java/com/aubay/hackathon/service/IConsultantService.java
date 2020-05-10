package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;

import java.util.Optional;

public interface IConsultantService {

    ConsultantCore save(ConsultantCore consultantCore);

    Optional<ConsultantCore> getById(Long id);
}
