package com.synapse.ai.volvo.Controller;

import com.synapse.ai.volvo.DTO.ProjectRequest;
import com.synapse.ai.volvo.DTO.ProjectResponse;
import com.synapse.ai.volvo.Service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/projects")
@RequiredArgsConstructor
@Tag(
        name = "User - Projects",
        description = "User APIs for submitting projects"
)
public class UserProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Create project",
            description = "Allows a user to submit a new project"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Project created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid project data"
            )
    })
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));
    }
}
