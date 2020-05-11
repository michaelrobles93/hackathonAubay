package com.aubay.hackathon.model.statistic;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AllStatistics {

    private Long accessCount;

    private Long allSolicitationCount;

    private Long allAuthorizedSolicitationCount;

    private List<StatisticConsultant> consultantView;

    private StatisticConsultant oldestDeallocateConsultant;

    private StatisticConsultant oldestConsultantView;
}

