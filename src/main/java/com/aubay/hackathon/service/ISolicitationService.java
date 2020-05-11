package com.aubay.hackathon.service;

import com.aubay.hackathon.model.core.SolicitationCore;

public interface ISolicitationService {

    SolicitationCore save(SolicitationCore solicitationCore);

    SolicitationCore updateManager(SolicitationCore solicitationCore);

    SolicitationCore updateAuthorize(SolicitationCore solicitationCore) throws Exception;

    Long getAllSolicitation();

    Long getAllSolicitationAuthorized();
}
