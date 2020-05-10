package com.aubay.hackathon.model.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "REQUEST")
public class SolicitationTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long consultantId;

    @NotNull
    private Long clientId;

    private Long managerId;

    @NotNull
    private LocalDate creationDate;

    private LocalDate authorizationDate;

    private boolean isAuthorized;
}
