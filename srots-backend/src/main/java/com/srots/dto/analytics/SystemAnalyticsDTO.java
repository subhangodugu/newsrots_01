package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemAnalyticsDTO {
    private SystemStatsDTO stats;
    private List<LeaderboardDTO> leaderboard;
    private List<PlacementProgressDTO> placementProgress;
    private List<BranchDistributionDTO> branchDistribution;
}
