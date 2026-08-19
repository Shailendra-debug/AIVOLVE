package com.synapse.ai.volvo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;


    @Column(name = "roll_number", nullable = false)
    private String rollNumber;

    @Column(nullable = false)
    private String Year;

    // 1. Overall experience: 1–5
    @Column(nullable = false)
    private Integer overallExperience;

    // 2. Organisation & management: 1–5
    @Column(nullable = false)
    private Integer organizationManagement;

    // 3. Activities & sessions: 1–5
    @Column(nullable = false)
    private Integer activitiesSessions;

    // 4. What did you like most?
    @Column(columnDefinition = "TEXT")
    private String likedMost;

    // 5. Suggestions for improvement
    @Column(columnDefinition = "TEXT")
    private String suggestions;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}