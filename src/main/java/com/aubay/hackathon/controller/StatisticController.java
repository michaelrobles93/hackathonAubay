package com.aubay.hackathon.controller;

import com.aubay.hackathon.model.core.ConsultantCore;
import com.aubay.hackathon.model.response.ConsultantResponse;
import com.aubay.hackathon.model.statistic.AllStatistics;
import com.aubay.hackathon.model.statistic.StatisticConsultant;
import com.aubay.hackathon.service.IConsultantService;
import com.aubay.hackathon.service.ISolicitationService;
import com.aubay.hackathon.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.aubay.hackathon.constants.ApplicationConstant.STATISTIC_END_POINT;
import static java.time.temporal.ChronoUnit.DAYS;

@RestController
@RequestMapping(STATISTIC_END_POINT)
public class StatisticController {

    private IUserService userService;

    private IConsultantService consultantService;

    private ISolicitationService solicitationService;

    private ModelMapper mapper;

    @Autowired
    public StatisticController(IUserService userService, IConsultantService consultantService, ISolicitationService solicitationService, ModelMapper mapper) {
        this.userService = userService;
        this.consultantService = consultantService;
        this.solicitationService = solicitationService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/all/access", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccessApplication() {

        return ResponseEntity.ok(userService.getAllAccess());
    }

    @GetMapping(value = "/all//solicitation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSolicitation() {

        return ResponseEntity.ok(solicitationService.getAllSolicitation());
    }

    @GetMapping(value = "/all/solicitation/authorized", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSolicitationAuthorized() {

        return ResponseEntity.ok(solicitationService.getAllSolicitationAuthorized());
    }

    @GetMapping(value = "/view/ranking/consultant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getViewConsultant() {

        getStatisticConsultantList();
        return ResponseEntity.ok(getStatisticConsultantList());
    }

    @GetMapping(value = "/view/oldest/consultant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOldestViewConsultant() {

        ConsultantCore consultantCore = consultantService.findAllByAuthorizedIsFalseOrderByLastViewAsc();

        return ResponseEntity.ok(StatisticConsultant.builder()
                .consultantResponse(mapper.map(consultantCore, ConsultantResponse.class))
                .views(consultantCore.getViews())
                .daysWithNoView(DAYS.between(consultantCore.getLastView().toLocalDate(), LocalDate.now()))
                .daysDeallocate(DAYS.between(consultantCore.getCreationDate().toLocalDate(), LocalDate.now()))
                .build()
        );
    }

    @GetMapping(value = "/deallocate/oldest/consultant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOldestDeallocateConsultant() {

        ConsultantCore consultantCore = consultantService.findAllByAuthorizedIsFalseOrderByCreatedDateAsc();

        return ResponseEntity.ok(StatisticConsultant.builder()
                .consultantResponse(mapper.map(consultantCore, ConsultantResponse.class))
                .views(consultantCore.getViews())
                .daysWithNoView(DAYS.between(consultantCore.getLastView().toLocalDate(), LocalDate.now()))
                .daysDeallocate(DAYS.between(consultantCore.getCreationDate().toLocalDate(), LocalDate.now()))
                .build()
        );
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllStatistics() {

        Long allAccess = userService.getAllAccess();
        Long allSolicitation = solicitationService.getAllSolicitation();
        Long authorizedSolicitation = solicitationService.getAllSolicitationAuthorized();
        ConsultantCore consultantCoreOldestView = consultantService.findAllByAuthorizedIsFalseOrderByLastViewAsc();
        ConsultantCore consultantCoreOldestDeallocate = consultantService.findAllByAuthorizedIsFalseOrderByCreatedDateAsc();

        return ResponseEntity.ok(AllStatistics.builder()
                .accessCount(allAccess)
                .allSolicitationCount(allSolicitation)
                .allAuthorizedSolicitationCount(authorizedSolicitation)
                .consultantView(getStatisticConsultantList())
                .oldestConsultantView(StatisticConsultant.builder()
                        .consultantResponse(mapper.map(consultantCoreOldestView, ConsultantResponse.class))
                        .views(consultantCoreOldestView.getViews())
                        .daysWithNoView(DAYS.between(consultantCoreOldestView.getLastView().toLocalDate(), LocalDate.now()))
                        .daysDeallocate(DAYS.between(consultantCoreOldestView.getCreationDate().toLocalDate(), LocalDate.now()))
                        .build())
                .oldestDeallocateConsultant(StatisticConsultant.builder()
                        .consultantResponse(mapper.map(consultantCoreOldestDeallocate, ConsultantResponse.class))
                        .views(consultantCoreOldestDeallocate.getViews())
                        .daysWithNoView(DAYS.between(consultantCoreOldestDeallocate.getLastView().toLocalDate(), LocalDate.now()))
                        .daysDeallocate(DAYS.between(consultantCoreOldestDeallocate.getCreationDate().toLocalDate(), LocalDate.now()))
                        .build())
                .build());
    }

    private List<StatisticConsultant> getStatisticConsultantList() {
        List<StatisticConsultant> statisticConsultantList = new ArrayList<>();
        List<ConsultantCore> coreList = consultantService.findAllByOrderByViewsDesc();
        coreList.forEach(core -> statisticConsultantList.add(StatisticConsultant.builder()
                .consultantResponse(mapper.map(core, ConsultantResponse.class))
                .views(core.getViews())
                .daysWithNoView(DAYS.between(core.getLastView().toLocalDate(), LocalDate.now()))
                .daysDeallocate(DAYS.between(core.getCreationDate().toLocalDate(), LocalDate.now()))
                .build()
        ));

        return statisticConsultantList;
    }

}
