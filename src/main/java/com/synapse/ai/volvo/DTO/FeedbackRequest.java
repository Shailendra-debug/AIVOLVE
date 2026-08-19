package com.synapse.ai.volvo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request payload for submitting event feedback")
public class FeedbackRequest {



    private String name;

    private String rollNumber;

    private String year;

    @NotNull(message = "Overall experience rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Schema(
            description = "Overall experience rating",
            example = "5",
            minimum = "1",
            maximum = "5"
    )
    private Integer overallExperience;


    @NotNull(message = "Organization and management rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Schema(
            description = "Rating for event organization and management",
            example = "4",
            minimum = "1",
            maximum = "5"
    )
    private Integer organizationManagement;


    @NotNull(message = "Activities and sessions rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    @Schema(
            description = "Satisfaction with activities and sessions",
            example = "5",
            minimum = "1",
            maximum = "5"
    )
    private Integer activitiesSessions;


    @Size(max = 2000, message = "Feedback must not exceed 2000 characters")
    @Schema(
            description = "What the participant liked most about the event",
            example = "I really liked the interactive activities and technical sessions.",
            maxLength = 2000
    )
    private String likedMost;


    @Size(max = 2000, message = "Suggestions must not exceed 2000 characters")
    @Schema(
            description = "Suggestions for improving future events",
            example = "More time could be provided for Q&A and networking.",
            maxLength = 2000
    )
    private String suggestions;
}