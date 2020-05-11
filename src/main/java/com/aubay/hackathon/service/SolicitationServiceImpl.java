package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.core.SolicitationCore;
import com.aubay.hackathon.model.table.SolicitationTable;
import com.aubay.hackathon.repository.SolicitationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SolicitationServiceImpl implements ISolicitationService {

    private SolicitationRepository repository;

    private IConsultantService consultantService;

    private ModelMapper mapper;

    @Autowired
    public SolicitationServiceImpl(SolicitationRepository repository, IConsultantService consultantService, ModelMapper mapper) {
        this.repository = repository;
        this.consultantService = consultantService;
        this.mapper = mapper;
    }

    @Override
    public SolicitationCore save(SolicitationCore solicitationCore) {

        SolicitationCore solicitationCoreValid = SolicitationCore.builder()
                .creationDate(LocalDateTime.now())
                .consultantId(solicitationCore.getConsultantId())
                .clientId(solicitationCore.getClientId())
                .isAuthorized(false)
                .build();

        return mapper.map(repository.save(mapper.map(solicitationCoreValid, SolicitationTable.class)), SolicitationCore.class);
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
    @Transactional
    public SolicitationCore updateAuthorize(SolicitationCore solicitationCore) throws Exception {

        Optional<SolicitationTable> table = repository.findById(solicitationCore.getId());
        if (table.isPresent()) {
            SolicitationCore solicitationCoreValid = mapper.map(table.get(), SolicitationCore.class);
            solicitationCoreValid.setAuthorized(solicitationCore.isAuthorized());
            solicitationCoreValid.setAuthorizationDate(LocalDateTime.now());
            solicitationCore = mapper.map(repository.save(mapper.map(solicitationCoreValid, SolicitationTable.class)), SolicitationCore.class);
            updateConsultantAuthorized(solicitationCoreValid.getConsultantId(), solicitationCoreValid.isAuthorized());
            return solicitationCore;
        }

        return null;
    }

    @Override
    public Long getAllSolicitation() {

        return repository.getAllSolicitation();
    }

    @Override
    public Long getAllSolicitationAuthorized() {

        return repository.getAllSolicitationAuthorized();
    }

    private void updateConsultantAuthorized(Long consultantId, boolean isAuthorized) throws Exception {

        Optional<ConsultantCore> consultantCore = consultantService.getById(consultantId);
        if (consultantCore.isPresent()) {
            consultantCore.get().setAuthorized(isAuthorized);
            consultantService.save(consultantCore.get());
            return;
        }
        throw new Exception("Consultant not found");
    }
}
