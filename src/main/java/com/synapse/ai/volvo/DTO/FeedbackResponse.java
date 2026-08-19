package com.synapse.ai.volvo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Event feedback response")
public class FeedbackResponse {

    @Schema(
            description = "Unique feedback ID",
            example = "29dfaa52-25e1-4122-93b0-97c8c47b3643"
    )
    private UUID id;

    private String name;

    private String RollNumber;

    private String Year;


    @Schema(
            description = "Overall experience rating",
            example = "5",
            minimum = "1",
            maximum = "5"
    )
    private Integer overallExperience;


    @Schema(
            description = "Organization and management rating",
            example = "4",
            minimum = "1",
            maximum = "5"
    )
    private Integer organizationManagement;


    @Schema(
            description = "Activities and sessions rating",
            example = "5",
            minimum = "1",
            maximum = "5"
    )
    private Integer activitiesSessions;


    @Schema(
            description = "What the participant liked most",
            example = "The interactive activities were excellent."
    )
    private String likedMost;


    @Schema(
            description = "Suggestions for future events",
            example = "Add more networking sessions."
    )
    private String suggestions;


    @Schema(
            description = "Feedback submission date and time",
            example = "2026-08-19T13:30:00"
    )
    private LocalDateTime createdAt;
}