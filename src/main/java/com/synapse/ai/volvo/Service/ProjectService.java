package com.synapse.ai.volvo.Service;


import com.synapse.ai.volvo.DTO.CheckProject;
import com.synapse.ai.volvo.DTO.ProjectRequest;
import com.synapse.ai.volvo.DTO.ProjectResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse getProjectById(UUID id);

    List<ProjectResponse> getAllProjects();

    ProjectResponse updateProject(UUID id, ProjectRequest request);

    void deleteProject(UUID id);

    List<ProjectResponse> getProjectsByBranch(String branch);

    List<ProjectResponse> getProjectsByYear(String year);

    List<ProjectResponse> getProjectsBySection(String sec);

    List<ProjectResponse> getCheckedProjects();

    List<ProjectResponse> getUncheckedProjects();

    List<ProjectResponse> getProjectsByMarksDesc();


    ProjectResponse CheckProject(CheckProject makes);

    ProjectResponse uncheckProject(UUID id);
}
