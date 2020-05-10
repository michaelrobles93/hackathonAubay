package com.aubay.hackathon.model.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultantCore {

    private Long id;

    private String name;

    private String availability;

    private String aubayConsultantId;

    private String expertises;

    private String seniority;

    private String jobDescription;

    private String location;

    private String language;

    private String nationality;

    private String skills;

    private boolean isAuthorized;

    private LocalDate creationDate;
}
