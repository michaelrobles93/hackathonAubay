package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.filter.ConsultantFilter;
import com.aubay.hackathon.model.table.ConsultantTable;
import com.aubay.hackathon.repository.ConsultantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Optional<ConsultantCore> getByIdIncrementView(Long id) {

        Optional<ConsultantTable> consultantTable = repository.findById(id);
        if (consultantTable.isPresent()) {
            consultantTable.get().setViews(consultantTable.get().getViews() + 1);
            consultantTable.get().setLastView(LocalDateTime.now());
            repository.save(consultantTable.get());
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

    @Override
    public List<ConsultantCore> findAllByOrderByViewsDesc() {

        List<ConsultantTable> tableList = repository.findAllByOrderByViewsDesc();
        List<ConsultantCore> coreList = new ArrayList<>();
        tableList.forEach(table -> coreList.add(mapper.map(table, ConsultantCore.class)));

        return coreList;
    }

    @Override
    public ConsultantCore findAllByAuthorizedIsFalseOrderByLastViewAsc() {

        List<ConsultantTable> tableList = repository.findAllByIsAuthorizedIsFalseOrderByLastViewAsc();
        return mapper.map(tableList.stream().findFirst().get(), ConsultantCore.class);
    }

    @Override
    public ConsultantCore findAllByAuthorizedIsFalseOrderByCreatedDateAsc() {

        List<ConsultantTable> tableList = repository.findAllByIsAuthorizedIsFalseOrderByCreationDateAsc();
        return mapper.map(tableList.stream().findFirst().get(), ConsultantCore.class);
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
                    .filter(skill -> !("," + core.getSkills() + ",").toLowerCase().replaceAll("\\s+","").contains(("," + skill + ",").toLowerCase()))
                    .findFirst().isPresent());
        }

        if (filter.getExpertises() != null) {
            coreList.removeIf(core -> filter.getExpertises().stream()
                    .filter(expertise -> !("," + core.getExpertises() + ",").toLowerCase().replaceAll("\\s+","").contains(("," + expertise + ",").toLowerCase()))
                    .findFirst().isPresent());
        }

        if (filter.getLanguages() != null) {
            coreList.removeIf(core -> filter.getLanguages().stream()
                    .filter(language -> !("," + core.getLanguage() + ",").toLowerCase().replaceAll("\\s+","").contains(("," + language + ",").toLowerCase()))
                    .findFirst().isPresent());
        }

        if (filter.getAvailability() != null) {
            coreList.removeIf(core -> !core.getAvailability().toLowerCase().equals(filter.getAvailability().toLowerCase()));
        }

        if (filter.getSeniority() != null) {
            coreList.removeIf(core -> !core.getSeniority().toLowerCase().equals(filter.getSeniority().toLowerCase()));
        }

        if (filter.getLocation() != null) {
            coreList.removeIf(core -> !core.getLocation().toLowerCase().equals(filter.getLocation().toLowerCase()));
        }
    }
}
