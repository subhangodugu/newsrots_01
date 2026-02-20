package com.srots.dto.jobdto;

import java.time.LocalDateTime;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationListDTO implements Serializable {

    @JsonProperty("job") // Explicitly tell Jackson to include this
    private JobSummary job;

    @JsonProperty("status") // Explicitly tell Jackson to include this
    private String status;

    @JsonProperty("appliedDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedDate;

    public ApplicationListDTO() {
    }

    public ApplicationListDTO(JobSummary job, String status, LocalDateTime appliedDate) {
        this.job = job;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    @Getter
    @Setter
    public static class JobSummary implements Serializable {
        @JsonProperty("id")
        private String id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("company")
        private String company;
        @JsonProperty("type")
        private String type;
        @JsonProperty("location")
        private String location;

        public JobSummary() {
        }

        public JobSummary(String id, String title, String company, String type, String location) {
            this.id = id;
            this.title = title;
            this.company = company;
            this.type = type;
            this.location = location;
        }
    }

}