package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementProgressDTO {
    private Integer year;
    private Integer month;
    private String label; // month name
    private Long value; // count
}
