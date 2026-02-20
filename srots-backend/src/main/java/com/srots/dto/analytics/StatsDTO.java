package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO {
    private Long totalStudents;
    private Long placedStudents;
    private Double placementRate;
    private Long companiesVisited;
}
