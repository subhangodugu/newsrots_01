package com.srots.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.srots.model.Job.JobType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTypeDTO {
    private String name;
    private Long count;

    public JobTypeDTO(JobType type, Long count) {
        this.name = type != null ? type.getDisplay() : "Unknown";
        this.count = count;
    }
}
