package com.aubay.hackathon.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ConsultantRequest {

    @NotNull
    private String name;

    @NotNull
    private String availability;

    @NotNull
    private String expertises;

    @NotNull
    private String seniority;

    @NotNull
    private String jobDescription;

    //private String aubayConsultantId;

    @NotNull
    private String location;

    @NotNull
    private String language;

    @NotNull
    private String nationality;

    @NotNull
    private String skills;

}
