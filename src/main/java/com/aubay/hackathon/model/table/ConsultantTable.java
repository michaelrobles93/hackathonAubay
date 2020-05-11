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
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity(name = "CONSULTANT")
public class    ConsultantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String availability;

    private String aubayConsultantId;

    @NotNull
    private String expertises;

    @NotNull
    private String seniority;

    @NotNull
    private String jobDescription;

    @NotNull
    private String location;

    @NotNull
    private String language;

    @NotNull
    private String nationality;

    @NotNull
    private String skills;

    @NotNull
    private boolean isAuthorized;

    private Long views;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private LocalDateTime lastView;
}
