package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.table.ConsultantTable;
import com.aubay.hackathon.repository.ConsultantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ConsultantServiceImpl implements IConsultantService {

    private ConsultantRepository repository;

    private ModelMapper mapper;

    @Autowired
    public ConsultantServiceImpl(ConsultantRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConsultantCore save(ConsultantCore consultantCore) {

        consultantCore.setAuthorized(false);
        consultantCore.setCreationDate(LocalDate.now());
        return mapper.map(repository.save(mapper.map(consultantCore, ConsultantTable.class)), ConsultantCore.class);
    }

    @Override
    public Optional<ConsultantCore> getById(Long id) {

        Optional<ConsultantTable> consultantTable = repository.findById(id);
        if (consultantTable.isPresent()) {
            return Optional.of(mapper.map(consultantTable.get(), ConsultantCore.class));
        }

        return Optional.empty();
    }
}
