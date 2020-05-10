package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.filter.ConsultantFilter;
import com.aubay.hackathon.model.table.ConsultantTable;
import com.aubay.hackathon.repository.ConsultantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ConsultantCore> getFilteredConsultant(ConsultantFilter consultantFilter) {

        List<ConsultantTable> tableList = repository.findAll();
        if (tableList.isEmpty()) {
            return new ArrayList<>();
        }

        List<ConsultantCore> coreList = getConsultantCoreFromTableList(tableList);
        filterConsultantCore(coreList, consultantFilter);

        return coreList;
    }

    private List<ConsultantCore> getConsultantCoreFromTableList(List<ConsultantTable> tableList) {

        List<ConsultantCore> coreList = new ArrayList<>();
        tableList.forEach(table -> coreList.add(mapper.map(table, ConsultantCore.class)));
        return coreList;
    }

    private void filterConsultantCore(List<ConsultantCore> coreList, ConsultantFilter filter) {

        if (coreList.isEmpty()) {
            return;
        }

        if (filter.getSkills() != null) {
            coreList.removeIf(core -> filter.getSkills().stream()
                    .filter(skill -> !core.getSkills().toLowerCase().contains(skill.toLowerCase()))
                    .findFirst().isPresent());
        }

        if (filter.getExpertises() != null) {
            coreList.removeIf(core -> filter.getExpertises().stream()
                    .filter(expertise -> !core.getExpertises().toLowerCase().contains(expertise.toLowerCase()))
                    .findFirst().isPresent());
        }

    }
}
