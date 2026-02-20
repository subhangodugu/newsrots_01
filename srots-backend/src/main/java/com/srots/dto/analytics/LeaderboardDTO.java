package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardDTO {
    private String name;
    private String placement; // percentage e.g. "85%"
    private Long jobs;
}
