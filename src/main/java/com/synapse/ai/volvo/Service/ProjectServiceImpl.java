package com.synapse.ai.volvo.Service;


import com.synapse.ai.volvo.DTO.CheckProject;
import com.synapse.ai.volvo.DTO.ProjectRequest;
import com.synapse.ai.volvo.DTO.ProjectResponse;
import com.synapse.ai.volvo.Entity.Project;
import com.synapse.ai.volvo.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {

        Project project = Project.builder()
                .name(request.getName())
                .roll(request.getRoll())
                .year(request.getYear())
                .branch(request.getBranch())
                .sec(request.getSec())
                .themes(request.getThemes())
                .description(request.getDescription())
                .liveUrl(request.getLiveUrl())
                .checked(false)
                .marks(0)
                .zipFileUrl(request.getZipFileUrl())
                .build();

        Project savedProject = projectRepository.save(project);

        return mapToResponse(savedProject);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectResponse getProjectById(UUID id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + id)
                );

        return mapToResponse(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProjectResponse updateProject(UUID id, ProjectRequest request) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + id)
                );

        project.setName(request.getName());
        project.setRoll(request.getRoll());
        project.setYear(request.getYear());
        project.setBranch(request.getBranch());
        project.setSec(request.getSec());
        project.setThemes(request.getThemes());
        project.setDescription(request.getDescription());
        project.setLiveUrl(request.getLiveUrl());
        project.setZipFileUrl(request.getZipFileUrl());

        Project updatedProject = projectRepository.save(project);

        return mapToResponse(updatedProject);
    }

    @Override
    public void deleteProject(UUID id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + id)
                );

        projectRepository.delete(project);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByBranch(String branch) {

        return projectRepository.findByBranchIgnoreCase(branch)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByYear(String year) {

        return projectRepository.findByYear(year)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsBySection(String sec) {

        return projectRepository.findBySecIgnoreCase(sec)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProjectResponse mapToResponse(Project project) {

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .roll(project.getRoll())
                .year(project.getYear())
                .branch(project.getBranch())
                .sec(project.getSec())
                .themes(project.getThemes())
                .description(project.getDescription())
                .liveUrl(project.getLiveUrl())
                .checked(project.getChecked())
                .zipFileUrl(project.getZipFileUrl())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getCheckedProjects() {

        return projectRepository.findByCheckedTrue()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getUncheckedProjects() {

        return projectRepository.findByCheckedFalse()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByMarksDesc() {

        return projectRepository.findAllByOrderByMarksDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProjectResponse CheckProject(CheckProject makes) {

        Project project = projectRepository.findById(makes.getId())
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + makes.getId())
                );

        project.setChecked(true);
        project.setMarks(makes.getMarks());
        return mapToResponse(
                projectRepository.save(project)
        );
    }

    @Override
    public ProjectResponse uncheckProject(UUID id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + id)
                );

        project.setChecked(false);

        return mapToResponse(
                projectRepository.save(project)
        );
    }
}
