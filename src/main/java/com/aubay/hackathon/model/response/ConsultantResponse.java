package com.aubay.hackathon.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultantResponse {

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
}
