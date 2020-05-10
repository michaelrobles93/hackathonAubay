package com.aubay.hackathon.model.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitationCore {

    private Long id;

    private Long consultantId;

    private Long clientId;

    private Long managerId;

    private LocalDate creationDate;

    private LocalDate authorizationDate;

    private boolean isAuthorized;
}
