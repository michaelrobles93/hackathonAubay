package com.aubay.hackathon.controller;

import com.aubay.hackathon.model.core.SolicitationCore;
import com.aubay.hackathon.model.request.SolicitationRequest;
import com.aubay.hackathon.service.ISolicitationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.aubay.hackathon.constants.ApplicationConstant.SOLICITATION_END_POINT;

@RestController
@RequestMapping(SOLICITATION_END_POINT)
public class SolicitationController {

    private ISolicitationService solicitationService;

    private ModelMapper mapper;

    @Autowired
    public SolicitationController(ISolicitationService solicitationService, ModelMapper mapper) {
        this.solicitationService = solicitationService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRequest(@RequestBody @Valid SolicitationRequest solicitationRequest) {

        SolicitationCore solicitationCore = mapper.map(solicitationRequest, SolicitationCore.class);
        if (Optional.of(solicitationService.save(solicitationCore)).isPresent()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping(value = "/{solicitation_id}/manager", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateManagerRequest(@PathVariable("solicitation_id") Long solicitationId, @RequestBody SolicitationRequest solicitationRequest) {

        if (solicitationRequest.getManagerId() == null) {
            return ResponseEntity.badRequest().build();
        }
        SolicitationCore solicitationCore = mapper.map(solicitationRequest, SolicitationCore.class);
        solicitationCore.setId(solicitationId);
        if (Optional.of(solicitationService.updateManager(solicitationCore)).isPresent()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping(value = "/{solicitation_id}/authorize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAuthorizeRequest(@PathVariable("solicitation_id") Long solicitationId, @RequestBody SolicitationRequest solicitationRequest) throws Exception {

        SolicitationCore solicitationCore = mapper.map(solicitationRequest, SolicitationCore.class);
        solicitationCore.setId(solicitationId);
        if (solicitationService.updateAuthorize(solicitationCore) == null) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok().build();
    }

}
