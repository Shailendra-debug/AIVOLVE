package com.synapse.ai.volvo.Controller;

import com.synapse.ai.volvo.DTO.CheckProject;
import com.synapse.ai.volvo.DTO.ProjectRequest;
import com.synapse.ai.volvo.DTO.ProjectResponse;
import com.synapse.ai.volvo.Service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/projects")
@RequiredArgsConstructor
@Tag(
        name = "Admin - Project Management",
        description = "Admin APIs for complete project management"
)
public class AdminProjectController {

    private final ProjectService projectService;

    // ================= CREATE =================

    @Operation(summary = "Create project")
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(
            @Valid @RequestBody ProjectRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(projectService.createProject(request));
    }

    // ================= GET ALL =================

    @Operation(summary = "Get all projects")
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(
                projectService.getAllProjects()
        );
    }

    // ================= GET BY ID =================

    @Operation(summary = "Get project by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                projectService.getProjectById(id)
        );
    }

    // ================= UPDATE =================

    @Operation(summary = "Update project")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable UUID id,
            @Valid @RequestBody ProjectRequest request
    ) {
        return ResponseEntity.ok(
                projectService.updateProject(id, request)
        );
    }

    // ================= DELETE =================

    @Operation(summary = "Delete project")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable UUID id
    ) {
        projectService.deleteProject(id);

        return ResponseEntity.noContent().build();
    }

    // ================= CHECK PROJECT =================

    @Operation(
            summary = "Check project",
            description = "Marks a project as checked"
    )
    @PatchMapping("/check")
    public ResponseEntity<ProjectResponse> checkProject(
            @RequestBody CheckProject project
            ) {
        return ResponseEntity.ok(
                projectService.CheckProject(project)
        );
    }

    // ================= UNCHECK PROJECT =================

    @Operation(
            summary = "Uncheck project",
            description = "Marks a project as unchecked"
    )
    @PatchMapping("/{id}/uncheck")
    public ResponseEntity<ProjectResponse> uncheckProject(
            @PathVariable UUID id
    ) {
        return ResponseEntity.ok(
                projectService.uncheckProject(id)
        );
    }

    // ================= BY BRANCH =================

    @Operation(summary = "Get projects by branch")
    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByBranch(
            @PathVariable String branch
    ) {
        return ResponseEntity.ok(
                projectService.getProjectsByBranch(branch)
        );
    }

    // ================= BY YEAR =================

    @Operation(summary = "Get projects by year")
    @GetMapping("/year/{year}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByYear(
            @PathVariable String year
    ) {
        return ResponseEntity.ok(
                projectService.getProjectsByYear(year)
        );
    }

    // ================= BY SECTION =================

    @Operation(summary = "Get projects by section")
    @GetMapping("/section/{sec}")
    public ResponseEntity<List<ProjectResponse>> getProjectsBySection(
            @PathVariable String sec
    ) {
        return ResponseEntity.ok(
                projectService.getProjectsBySection(sec)
        );
    }

    // ================= CHECKED =================

    @Operation(summary = "Get checked projects")
    @GetMapping("/checked")
    public ResponseEntity<List<ProjectResponse>> getCheckedProjects() {
        return ResponseEntity.ok(
                projectService.getCheckedProjects()
        );
    }

    // ================= UNCHECKED =================

    @Operation(summary = "Get unchecked projects")
    @GetMapping("/unchecked")
    public ResponseEntity<List<ProjectResponse>> getUncheckedProjects() {
        return ResponseEntity.ok(
                projectService.getUncheckedProjects()
        );
    }

    // ================= SORT BY MARKS =================

    @Operation(
            summary = "Get projects sorted by marks",
            description = "Returns projects from highest marks to lowest marks"
    )
    @GetMapping("/sorted-by-marks")
    public ResponseEntity<List<ProjectResponse>> getProjectsByMarksDesc() {
        return ResponseEntity.ok(
                projectService.getProjectsByMarksDesc()
        );
    }
}
