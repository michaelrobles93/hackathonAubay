package com.aubay.hackathon.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultantFilter {

    private List<String> skills;

    private List<String> expertises;

    private String location;

    private String availibity;

    private String seniority;
}
