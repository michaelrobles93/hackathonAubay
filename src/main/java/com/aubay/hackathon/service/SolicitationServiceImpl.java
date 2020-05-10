package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.SolicitationCore;
import com.aubay.hackathon.model.table.SolicitationTable;
import com.aubay.hackathon.repository.SolicitationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SolicitationServiceImpl implements ISolicitationService {

    private SolicitationRepository repository;

    private ModelMapper mapper;

    @Autowired
    public SolicitationServiceImpl(SolicitationRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SolicitationCore save(SolicitationCore solicitationCore) {

        solicitationCore.setCreationDate(LocalDate.now());
        solicitationCore.setAuthorized(false);

        return mapper.map(repository.save(mapper.map(solicitationCore, SolicitationTable.class)), SolicitationCore.class);
    }

    @Override
    public SolicitationCore updateManager(SolicitationCore solicitationCore) {

        Optional<SolicitationTable> table = repository.findById(solicitationCore.getId());
        if (table.isPresent()) {
            SolicitationCore solicitationCoreValid = mapper.map(table.get(), SolicitationCore.class);
            solicitationCoreValid.setManagerId(solicitationCore.getManagerId());
            return mapper.map(repository.save(mapper.map(solicitationCoreValid, SolicitationTable.class)), SolicitationCore.class);
        }

        return null;

    }

    @Override
    public SolicitationCore updateAuthorize(SolicitationCore solicitationCore) {

        Optional<SolicitationTable> table = repository.findById(solicitationCore.getId());
        if (table.isPresent()) {
            SolicitationCore solicitationCoreValid = mapper.map(table.get(), SolicitationCore.class);
            solicitationCoreValid.setAuthorized(solicitationCore.isAuthorized());
            solicitationCoreValid.setAuthorizationDate(LocalDate.now());
            return mapper.map(repository.save(mapper.map(solicitationCoreValid, SolicitationTable.class)), SolicitationCore.class);
        }

        return null;
    }
}
