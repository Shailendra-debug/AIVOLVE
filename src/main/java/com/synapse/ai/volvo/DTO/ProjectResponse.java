package com.synapse.ai.volvo.DTO;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {

    private UUID id;

    private String name;

    private String roll;

    private String year;

    private String branch;

    private String sec;

    private String themes;

    private String description;

    private String liveUrl;

    private String zipFileUrl;

    private Boolean checked;

    private Integer marks;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
