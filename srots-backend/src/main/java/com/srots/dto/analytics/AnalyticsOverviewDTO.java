package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.srots.dto.jobdto.JobResponseDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsOverviewDTO {
    private List<BranchDistributionDTO> branchDistribution;
    private List<PlacementProgressDTO> placementProgress;
    private List<JobTypeDTO> jobTypes;
    private StatsDTO stats;
    private List<JobResponseDTO> recentJobs;
}
