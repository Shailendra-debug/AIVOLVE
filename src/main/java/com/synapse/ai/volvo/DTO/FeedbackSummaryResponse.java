package com.synapse.ai.volvo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Event feedback summary")
public class FeedbackSummaryResponse {

    @Schema(
            description = "Total number of feedback submissions",
            example = "120"
    )
    private Long totalFeedbacks;


    @Schema(
            description = "Average overall experience rating",
            example = "4.65"
    )
    private Double averageOverallExperience;


    @Schema(
            description = "Average organization and management rating",
            example = "4.52"
    )
    private Double averageOrganizationManagement;


    @Schema(
            description = "Average activities and sessions rating",
            example = "4.70"
    )
    private Double averageActivitiesSessions;
}