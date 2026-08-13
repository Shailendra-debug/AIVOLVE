package com.synapse.ai.volvo.Repository;



import com.synapse.ai.volvo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findByBranchIgnoreCase(String branch);

    List<Project> findByYear(String year);

    List<Project> findBySecIgnoreCase(String sec);

    List<Project> findByBranchIgnoreCaseAndYear(
            String branch,
            String year
    );

    List<Project> findByCheckedTrue();

    List<Project> findByCheckedFalse();

    List<Project> findAllByOrderByMarksDesc();
}
