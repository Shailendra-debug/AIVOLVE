package com.synapse.ai.volvo.Controller;

import com.synapse.ai.volvo.DTO.FeedbackRequest;
import com.synapse.ai.volvo.DTO.FeedbackResponse;
import com.synapse.ai.volvo.DTO.FeedbackSummaryResponse;
import com.synapse.ai.volvo.Service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
@Tag(
        name = "Feedback",
        description = "APIs for submitting and managing event feedback"
)
public class FeedbackController {

    private final FeedbackService feedbackService;


    // =========================
    // CREATE FEEDBACK
    // =========================

    @PostMapping
    @Operation(
            summary = "Submit feedback",
            description = "Submit feedback for an event"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Feedback submitted successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid feedback data"
            )
    })
    public ResponseEntity<FeedbackResponse> createFeedback(
            @Valid @RequestBody FeedbackRequest request
    ) {

        FeedbackResponse response =
                feedbackService.createFeedback(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // =========================
    // GET FEEDBACK BY ID
    // =========================

    @GetMapping("/{id}")
    @Operation(
            summary = "Get feedback by ID",
            description = "Fetch a specific feedback using its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Feedback found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Feedback not found"
            )
    })
    public ResponseEntity<FeedbackResponse> getFeedbackById(

            @Parameter(
                    description = "Feedback UUID",
                    required = true,
                    example = "29dfaa52-25e1-4122-93b0-97c8c47b3643"
            )
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                feedbackService.getFeedbackById(id)
        );
    }


    // =========================
    // GET ALL FEEDBACK
    // =========================

    @GetMapping
    @Operation(
            summary = "Get all feedback",
            description = "Fetch all submitted event feedback"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Feedback list fetched successfully"
    )
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {

        return ResponseEntity.ok(
                feedbackService.getAllFeedbacks()
        );
    }


    // =========================
    // FEEDBACK SUMMARY
    // =========================

    @GetMapping("/summary")
    @Operation(
            summary = "Get feedback summary",
            description = "Get overall feedback statistics and average ratings"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Feedback summary fetched successfully"
    )
    public ResponseEntity<FeedbackSummaryResponse> getFeedbackSummary() {

        return ResponseEntity.ok(
                feedbackService.getFeedbackSummary()
        );
    }


    // =========================
    // DELETE FEEDBACK
    // =========================

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete feedback",
            description = "Delete feedback using its UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Feedback deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Feedback not found"
            )
    })
    public ResponseEntity<Void> deleteFeedback(

            @Parameter(
                    description = "Feedback UUID",
                    required = true,
                    example = "29dfaa52-25e1-4122-93b0-97c8c47b3643"
            )
            @PathVariable UUID id
    ) {

        feedbackService.deleteFeedback(id);

        return ResponseEntity.noContent().build();
    }
}