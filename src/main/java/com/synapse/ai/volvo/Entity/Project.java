package com.synapse.ai.volvo.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "projects",
        indexes = {
                @Index(name = "idx_projects_roll", columnList = "roll"),
                @Index(name = "idx_projects_year", columnList = "year"),
                @Index(name = "idx_projects_branch", columnList = "branch"),
                @Index(name = "idx_projects_section", columnList = "section")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String roll;

    @Column(nullable = false, length = 20)
    private String year;

    @Column(nullable = false, length = 100)
    private String branch;

    @Column(name = "section", nullable = false, length = 20)
    private String sec;

    @Column(nullable = false, length = 255)
    private String themes;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Boolean checked;

    @Column(name = "live_url", length = 1000)
    private String liveUrl;

    @Column(name = "zip_file_url", length = 1000)
    private String zipFileUrl;

    @Column(nullable = false)
    @Builder.Default
    private Integer marks = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}