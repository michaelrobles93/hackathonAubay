package com.aubay.hackathon.model.statistic;

import com.aubay.hackathon.model.response.ConsultantResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class StatisticConsultant {

    private ConsultantResponse consultantResponse;

    private Long views;

    private Long daysWithNoView;

    private Long daysDeallocate;
}
