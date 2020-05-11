package com.aubay.hackathon.controller;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.filter.ConsultantFilter;
import com.aubay.hackathon.model.request.ConsultantRequest;
import com.aubay.hackathon.model.response.ConsultantResponse;
import com.aubay.hackathon.service.IConsultantService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.aubay.hackathon.constants.ApplicationConstant.CONSULTANT_END_POINT;

@RestController
@RequestMapping(CONSULTANT_END_POINT)
public class ConsultantController {

    private IConsultantService consultantService;

    private ModelMapper mapper;

    public ConsultantController(IConsultantService consultantService, ModelMapper mapper) {
        this.consultantService = consultantService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveConsultant(@RequestBody @Valid ConsultantRequest request) {

        ConsultantCore consultantCore = mapper.map(request, ConsultantCore.class);
        consultantCore.setAuthorized(false);
        consultantCore.setCreationDate(LocalDateTime.now());

        return ResponseEntity.ok(mapper.map(consultantService.save(consultantCore), ConsultantResponse.class));
    }

    @PutMapping(value = "/{consultant_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateConsultant(@PathVariable("consultant_id") Long consultantId, @RequestBody @Valid ConsultantRequest request) {

        ConsultantCore consultantCore = mapper.map(request, ConsultantCore.class);
        consultantCore.setId(consultantId);

        return ResponseEntity.ok(mapper.map(consultantService.save(consultantCore), ConsultantResponse.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConsultant(@PathVariable("id") Long id) {

        Optional<ConsultantCore> consultantCore = consultantService.getByIdIncrementView(id);
        if (consultantCore.isPresent()) {
            return ResponseEntity.ok(mapper.map(consultantCore.get(), ConsultantResponse.class));
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFilteredConsultants(@RequestBody ConsultantFilter consultantFilter) {

        List<ConsultantCore> coreList = consultantService.getFilteredConsultant(consultantFilter);
        if (coreList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ConsultantResponse> responseList = new ArrayList<>();
        coreList.forEach(core -> responseList.add(mapper.map(core, ConsultantResponse.class)));

        return ResponseEntity.ok(responseList);
    }
}
